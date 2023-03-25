package seedu.connectus.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.connectus.commons.exceptions.IllegalValueException;
import seedu.connectus.model.person.Address;
import seedu.connectus.model.person.Birthday;
import seedu.connectus.model.person.Email;
import seedu.connectus.model.person.Name;
import seedu.connectus.model.person.Person;
import seedu.connectus.model.person.Phone;
import seedu.connectus.model.socialmedia.SocialMedia;
import seedu.connectus.model.tag.Module;
import seedu.connectus.model.tag.Cca;
import seedu.connectus.model.tag.CcaPosition;
import seedu.connectus.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private JsonAdaptedPhone phone = new JsonAdaptedPhone();
    private JsonAdaptedEmail email = new JsonAdaptedEmail();
    private JsonAdaptedAddress address = new JsonAdaptedAddress();
    private JsonAdaptedSocialMedia socialMedia = new JsonAdaptedSocialMedia();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedModule> modules = new ArrayList<>();
    private final List<JsonAdaptedCca> ccas = new ArrayList<>();
    private final List<JsonAdaptedCcaPosition> ccaPositions = new ArrayList<>();
    private JsonAdaptedBirthday birthday = new JsonAdaptedBirthday();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") JsonAdaptedPhone phone,
            @JsonProperty("email") JsonAdaptedEmail email, @JsonProperty("address") JsonAdaptedAddress address,
            @JsonProperty("socialMedia") JsonAdaptedSocialMedia socialMedia,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("modules") List<JsonAdaptedModule> modules,
            @JsonProperty("ccas") List<JsonAdaptedCca> ccas,
            @JsonProperty("ccaPositions") List<JsonAdaptedCcaPosition> ccaPositions,
            @JsonProperty("birthday") JsonAdaptedBirthday birthday) {
        this.name = name;
        if (phone != null) {
            this.phone = phone;
        }
        if (email != null) {
            this.email = email;
        }
        if (address != null) {
            this.address = address;
        }
        if (socialMedia != null) {
            this.socialMedia = socialMedia;
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (modules != null) {
            this.modules.addAll(modules);
        }
        if (ccas != null) {
            this.ccas.addAll(ccas);
        }
        if (ccaPositions != null) {
            this.ccaPositions.addAll(ccaPositions);
        }
        if (birthday != null) {
            this.birthday = birthday;
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));

        modules.addAll(source.getModules().stream()
                .map(JsonAdaptedModule::new)
                .collect(Collectors.toList()));

        ccas.addAll(source.getCcas().stream()
                .map(JsonAdaptedCca::new)
                .collect(Collectors.toList()));

        ccaPositions.addAll(source.getCcaPositions().stream()
                .map(JsonAdaptedCcaPosition::new)
                .collect(Collectors.toList()));

        if (source.getPhone().isPresent()) {
            phone = new JsonAdaptedPhone(source.getPhone().get());
        }

        if (source.getEmail().isPresent()) {
            email = new JsonAdaptedEmail(source.getEmail().get());
        }

        if (source.getAddress().isPresent()) {
            address = new JsonAdaptedAddress(source.getAddress().get());
        }

        if (source.getSocialMedia().isPresent()) {
            socialMedia = new JsonAdaptedSocialMedia(source.getSocialMedia().get());
        }

        if (source.getBirthday().isPresent()) {
            birthday = new JsonAdaptedBirthday(source.getBirthday().get());
        }
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final List<Module> personModules = new ArrayList<>();
        for (JsonAdaptedModule module : modules) {
            personModules.add(module.toModelType());
        }

        final List<Cca> personCcas = new ArrayList<>();
        for (JsonAdaptedCca cca : ccas) {
            personCcas.add(cca.toModelType());
        }

        final List<CcaPosition> personCcaPositions = new ArrayList<>();
        for (JsonAdaptedCcaPosition ccaPosition : ccaPositions) {
            personCcaPositions.add(ccaPosition.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Set<Module> modelModules = new HashSet<>(personModules);
        final Set<Cca> modelCcas = new HashSet<>(personCcas);
        final Set<CcaPosition> modelCcaPositions = new HashSet<>(personCcaPositions);
        Person p = new Person(modelName, modelTags, modelModules, modelCcas, modelCcaPositions);

        if (phone != null) {
            Optional<Phone> modelPhone = phone.toModelType();
            if (modelPhone.isPresent()) {
                p.setPhone(modelPhone.get());
            }
        }

        if (email != null) {
            Optional<Email> modelEmail = email.toModelType();
            if (modelEmail.isPresent()) {
                p.setEmail(modelEmail.get());
            }
        }
        if (address != null) {
            Optional<Address> modelAddress = address.toModelType();
            if (modelAddress.isPresent()) {
                p.setAddress(modelAddress.get());
            }
        }

        if (birthday != null) {
            Optional<Birthday> modelBirthday = birthday.toModelType();
            if (modelBirthday.isPresent()) {
                p.setBirthday(modelBirthday.get());
            }
        }

        if (socialMedia != null) {
            Optional<SocialMedia> modelSocialMedia = socialMedia.toModelType();
            if (modelSocialMedia.isPresent()) {
                p.setSocialMedia(modelSocialMedia.get());
            }
        }

        return p;
    }

}
