package bearlib.motor;

import java.util.Optional;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase;

/**
 * The {@code ConfiguredMotor} class represents a fully configured motor,
 * encapsulating
 * the motor controller ({@link SparkBase}) and its associated encoders
 * ({@link RelativeEncoder} and {@link AbsoluteEncoder}).
 * 
 */
public final class ConfiguredMotor {

  private final SparkBase spark;
  private final RelativeEncoder relativeEncoder;
  private final AbsoluteEncoder absoluteEncoder;

  /**
   * Constructs a {@code ConfiguredMotor} instance with the specified motor
   * controller and encoders.
   * 
   * @param spark           the configured {@link SparkBase} motor controller
   * @param relativeEncoder the associated {@link RelativeEncoder}, or
   *                        {@code null} if not applicable
   * @param absoluteEncoder the associated {@link AbsoluteEncoder}, or
   *                        {@code null} if not applicable
   */
  public ConfiguredMotor(SparkBase spark, RelativeEncoder relativeEncoder, AbsoluteEncoder absoluteEncoder) {
    this.spark = spark;
    this.relativeEncoder = relativeEncoder;
    this.absoluteEncoder = absoluteEncoder;
  }

  /**
   * Returns the configured motor controller.
   * 
   * @return the {@link SparkBase} motor controller
   */
  public SparkBase getSpark() {
    return spark;
  }

  /**
   * Retrieves the relative encoder associated with the motor, if present.
   * 
   * @return an {@link Optional} containing the {@link RelativeEncoder}, or an
   *         empty {@code Optional} if no relative encoder is configured
   */
  public Optional<RelativeEncoder> getRelativeEncoder() {
    return Optional.ofNullable(relativeEncoder);
  }

  /**
   * Retrieves the absolute encoder associated with the motor, if present.
   * 
   * @return an {@link Optional} containing the {@link AbsoluteEncoder}, or an
   *         empty {@code Optional} if no absolute encoder is configured
   */
  public Optional<AbsoluteEncoder> getAbsoluteEncoder() {
    return Optional.ofNullable(absoluteEncoder);
  }

}
