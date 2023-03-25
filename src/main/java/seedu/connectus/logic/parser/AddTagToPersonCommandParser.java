package seedu.connectus.logic.parser;

import static seedu.connectus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.connectus.logic.commands.AddTagToPersonCommand.AddTagDescriptor;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.connectus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.connectus.commons.core.index.Index;
import seedu.connectus.logic.commands.AddTagToPersonCommand;
import seedu.connectus.logic.parser.exceptions.ParseException;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddTagToPersonCommand object
 */
public class AddTagToPersonCommandParser implements Parser<AddTagToPersonCommand> {
    @Override
    public AddTagToPersonCommand parse(String userInput) throws ParseException {
        var argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_TAG, PREFIX_MODULE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagToPersonCommand.MESSAGE_USAGE), pe);
        }

        var addTagDescriptor = new AddTagDescriptor(
            Optional.ofNullable(argMultimap.getAllValues(PREFIX_TAG)).map(l -> l.stream().map(Tag::new).collect(
                Collectors.toSet())).orElse(new HashSet<>()),
            Optional.ofNullable(argMultimap.getAllValues(PREFIX_MODULE)).map(l -> l.stream().map(Module::new).collect(
                Collectors.toSet())).orElse(new HashSet<>())
        );

        return new AddTagToPersonCommand(index, addTagDescriptor);
    }
}
