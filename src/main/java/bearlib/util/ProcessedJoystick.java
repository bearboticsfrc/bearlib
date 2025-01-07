package bearlib.util;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import java.util.function.Supplier;

/**
 * Provides an interface for accessing input from an Xbox controller, with utility methods to retrieve 
 * processed joystick axis data. The class is designed to work with a {@link CommandXboxController} and 
 * applies a {@link ThrottleProfile} to adjust the input values according to the current 'throttle' setting.
 */
public class ProcessedJoystick {

  /** 
   * The Xbox controller providing the joystick input. 
   */
  private final CommandXboxController joystick;

  /** 
   * Supplier function providing the current throttle profile. 
   */
  private Supplier<ThrottleProfile> throttleProfileSupplier;

  /** 
   * The maximum velocity for scaling the throttle profile. 
   */
  private final double maxVelocity;

  /**
   * Constructs a new {@code ProcessedJoystick} object.
   *
   * @param joystick The {@link CommandXboxController} from which joystick inputs are read.
   * @param throttleProfile A {@link Supplier} that provides the current {@link ThrottleProfile},
   *     determining how the input is scaled.
   * @param maxVelocity The maximum velocity used to scale the joystick input.
   */
  public ProcessedJoystick(
      CommandXboxController joystick, Supplier<ThrottleProfile> throttleProfile, double maxVelocity) {
    this.joystick = joystick;
    this.throttleProfileSupplier = throttleProfile;
    this.maxVelocity = maxVelocity;
  }

  /**
   * Retrieves and processes the joystick input from the specified axis.
   *
   * <p>The input is scaled based on the throttle profile's maximum velocity. The raw joystick input is
   * multiplied by the throttle profile's max velocity and negated to align with expected directional
   * behavior.
   *
   * @param axis The {@link JoystickAxis} to read from the controller.
   * @return The processed input value, adjusted based on the current {@link ThrottleProfile}.
   */
  public double get(JoystickAxis axis) {
    double rawInput;

    switch (axis) {
      case Ly:
        rawInput = joystick.getLeftY();
        break;
      case Lx:
        rawInput = joystick.getLeftX();
        break;
      case Ry:
        rawInput = joystick.getRightY();
        break;
      case Rx:
        rawInput = joystick.getRightX();
        break;
      default:
        rawInput = 0;
    }

    // Adjust the raw input based on the throttle profile's max velocity
    return -(rawInput * throttleProfileSupplier.get().getMaxVelocity(maxVelocity));
  }

  /**
   * Enum representing the various joystick axes on the Xbox controller.
   *
   * <p>This enum allows for clearer, more readable code when specifying which axis input to
   * retrieve.
   */
  public enum JoystickAxis {
    /** Left Y-axis (up-down movement of the left joystick). */
    Ly,

    /** Left X-axis (left-right movement of the left joystick). */
    Lx,

    /** Right Y-axis (up-down movement of the right joystick). */
    Ry,

    /** Right X-axis (left-right movement of the right joystick). */
    Rx;
  }

  /**
   * Enum for different throttle modes, representing the scaling factor for joystick input.
   */
  public enum ThrottleProfile {
    /** High-speed throttle mode, scaling input by 2. */
    TURBO(2),

    /** Normal throttle mode, scaling input by 1. */
    NORMAL(1),

    /** Low-speed throttle mode, scaling input by 0.25. */
    TURTLE(0.25);

    private final double maxVelocityMultiplier;

    private ThrottleProfile(double maxVelocityMultiplier) {
      this.maxVelocityMultiplier = maxVelocityMultiplier;
    }

    /**
     * Gets the maximum velocity multiplier for the velocity mode.
     *
     * @return The maximum velocity multiplier.
     */
    public double getMaxVelocityMultiplier() {
      return maxVelocityMultiplier;
    }

    /**
     * Gets the maximum velocity for the velocity mode.
     *
     * @param maxVelocity The maximum velocity to scale from.
     * @return The maximum velocity.
     */
    public double getMaxVelocity(double maxVelocity) {
      return maxVelocity * getMaxVelocityMultiplier();
    }
  }
}
