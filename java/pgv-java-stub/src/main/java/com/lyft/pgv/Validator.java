package com.lyft.pgv;

/**
 * {@code Validator} asserts the validity of a protobuf object.
 * @param <T>
 */
@FunctionalInterface
public interface Validator<T> {
    /**
     * Asserts validation rules on a protobuf object.
     *
     * @param proto the protobuf object to validate.
     * @throws ValidationException with the first validation error encountered.
     */
    void assertValid(T proto) throws ValidationException;

    /**
     * Checks validation rules on a protobuf object.
     *
     * @param proto the protobuf object to validate.
     * @return {@code true} if all rules are valid, {@code false} if not.
     */
    default boolean isValid(T proto) {
        try {
            assertValid(proto);
            return true;
        } catch (com.lyft.pgv.ValidationException ex) {
            return false;
        }
    }

    Validator ALWAYS_VALID = (proto) -> {
        // Do nothing. Always valid.
    };
}