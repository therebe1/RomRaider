package enginuity.logger.definition.convertor;

import static enginuity.util.ByteUtil.asInt;

import java.text.DecimalFormat;

public final class AcceleratorOpeningAngleConvertor implements EcuParameterConvertor {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0");

    public String convert(byte[] bytes) {
        double angle = (double) asInt(bytes) / 2.55;
        return AcceleratorOpeningAngleConvertor.DECIMAL_FORMAT.format(angle);
    }

    public String getUnits() {
        return "%";
    }
}