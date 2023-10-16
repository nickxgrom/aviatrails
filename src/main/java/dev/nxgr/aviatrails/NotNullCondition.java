package dev.nxgr.aviatrails;

import org.modelmapper.Condition;
import org.modelmapper.spi.MappingContext;

public class NotNullCondition implements Condition {
    @Override
    public boolean applies(MappingContext context) {
        return context.getSource() != null;
    }

}