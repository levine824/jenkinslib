package com.levine824.jenkins.config

enum ConfigType {
    GENERAL, STAGE, STEP, POST

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
}