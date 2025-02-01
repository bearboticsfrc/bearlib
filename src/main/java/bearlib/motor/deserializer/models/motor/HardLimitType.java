package bearlib.motor.deserializer.models.motor;

import com.revrobotics.spark.config.LimitSwitchConfig;

/**
 * Represents the type of a hard limit.
 */
public enum HardLimitType {

    /**
     * Specifies the hard limit is normally open.
     */
    OPEN(LimitSwitchConfig.Type.kNormallyOpen),

    /**
     * Specifies the hard limit is normally closed.
     */
    CLOSED(LimitSwitchConfig.Type.kNormallyClosed);

    private final LimitSwitchConfig.Type type;

    private HardLimitType(LimitSwitchConfig.Type type) {
      this.type = type;
    }

    public LimitSwitchConfig.Type getType() {
      return type;
    }
}
