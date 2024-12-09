package com.levine824.jenkins.config

enum ConfigType {
    GENERAL, STAGE, STEP

    @Override
    String toString() {
        return this.name().toLowerCase()
    }

    static ConfigType fromString(String text) {
        for (ConfigType type : values()) {
            if (type.toString() == text) {
                return type
            }
        }
        return null
    }

    private static final String FIELD_ENDS = "_CONFIG_KEYS"

    String toField() {
        return this.name() + FIELD_ENDS
    }

    static ConfigType fromField(String text) {
        if (!text.endsWith(FIELD_ENDS)) {
            return null
        }
        String typeStr = text.substring(0, text.length() - FIELD_ENDS.length())
        return fromString(typeStr)
    }
}