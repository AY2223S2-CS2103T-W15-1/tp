package seedu.connectus.logic.commands;

import seedu.connectus.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Entering '" + COMMAND_WORD + "' alone returns a separate window with the link to the User Guide.\n"
            + "Entering '" + COMMAND_WORD + " [COMMAND]' will return the usage instructions for the specified command.\n"
            + "Example: '" + COMMAND_WORD + " add";

    private final boolean isHelpWindowShown;
    private final String helpMessage;
    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public HelpCommand() {
        this.isHelpWindowShown = true;
        this.helpMessage = SHOWING_HELP_MESSAGE;
    }

    public HelpCommand(String command) {
        this.isHelpWindowShown = false;
        this.helpMessage = command;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(helpMessage, isHelpWindowShown, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && isHelpWindowShown == ((HelpCommand) other).isHelpWindowShown
                && helpMessage.equals(((HelpCommand) other).helpMessage));
    }
}
