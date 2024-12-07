package com.levine824.jenkins.configuration

enum ConfigurationType {
    GENERAL, STAGE, STEP

    @Override
    String toString() {
        return this.name().toLowerCase()
    }

    static ConfigurationType fromString(String text) {
        for (ConfigurationType type : values()) {
            if (type.toString() == text) {
                return type
            }
        }
        return null
    }
}