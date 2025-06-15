package domainpackage;

import java.math.BigDecimal;


// https://schmidtdennis.hashnode.dev/validator-design-pattern
public class CakeValidator {
    public static ErrorCake validate(AbstractCake cake) {
        if (cake.getHersteller() == null) {
            return ErrorCake.NULL_VALUE;
        }
        if (cake.getHersteller().getName().trim().isEmpty()) {
            return ErrorCake.NO_NAME;
        }

        if (cake.getNaehrwert() <= 0) {
            return ErrorCake.TOO_SMALL;
        }
        if (cake.getHaltbarkeit() == null) {
            return ErrorCake.NULL_VALUE;
        }
        if (!cake.getHaltbarkeit().isPositive()) {
            return ErrorCake.TOO_SMALL;
        }
        if (cake.getPreis() == null) {
            return ErrorCake.NULL_VALUE;
        }
        //https:stackoverflow.com/questions/4164521/compare-if-bigdecimal-is-greater-than-zero
        if (cake.getPreis().compareTo(BigDecimal.ZERO) <= 0) {
            return ErrorCake.TOO_SMALL;
        }

        return null;

    }
}
