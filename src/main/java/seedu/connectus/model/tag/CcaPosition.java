package seedu.connectus.model.tag;

/**
 * Represents a Cca position in the ConnectUS.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class CcaPosition extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "CCA Position should be alphanumeric";
    public final String ccaPositionName;

    /**
     * Constructs a {@code Module}.
     *
     * @param ccaPositionName A valid CCA position.
     */
    public CcaPosition(String ccaPositionName) {
        super(ccaPositionName, MESSAGE_CONSTRAINTS);
        this.ccaPositionName = ccaPositionName;
    }

    /**
     * Returns true if a given string is a valid cca position name.
     */
    public static boolean isValidCcaPositionName(String test) {
        return isValidTagName(test);
    }
}
