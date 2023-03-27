---
layout: page
title: User Guide
---

ConnectUS is a desktop app for **managing contacts, optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ConnectUS can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Using this guide
- For instructions on setting up ConnectUS, please refer to [Quick start](#quick-start).
- For a table of commands, please refer to [Command summary](#command-summary).
- For detailed instructions on how to use each command, please refer to [Features](#features).

## Quick start

1. Ensure you have Java `11`(found [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html)) or above installed in your computer.

2. Download the latest `ConnectUS.jar` from [here](https://github.com/AY2223S2-CS2103T-W15-1/tp/releases).<br>
   ![Latest](images/ConnectUSLatest.png)

3. Copy the file to the folder you want to use as the _home folder_ for your ConnectUS app.<br>
   ![Home Folder](images/ConnectUSHomeFolder.png)

4. Double-click the `ConnectUS.jar` file to start the app.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   ![Command Box](images/ConnectUSCommandBox.png) <br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/James p/12345678 e/james@example.com a/Clementi tg/itsjameshere b/14/02/2000` : Adds a contact named `James` to ConnectUS.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [b/BIRTHDAY]` can be used as `n/John Doe b/14/02/2000` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[mod/MODULE_CODE]…​` can be used as ` ` (i.e. 0 times), `mod/CS2103T`, `mod/CS2103T mod/CS2107` etc.

* Information fields can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If an information field is expected only once in the command, but you specify it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous information fields for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

* Commands are case-sensitive!<br>
  e.g. if you specify `ADD n/James` or `aDd n/James` instead of `add n/James`, ConnectUS will not register it as a valid command.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a person: `add`

Adds a person to the ConnectUS app.

Format: `add n/NAME [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [tg/TELEGRAM] [ig/INSTAGRAM] [wa/WHATSAPP] [b/BIRTHDAY] [mod/MODULE_CODE]…​ [cca/CCA]…​ [ccapos/POSITION]…​ [t/TAG]…​` 

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags, modules and CCAs! (including 0)
</div>

Examples:
* `add n/James` would create a contact named James without any other contact information.
* `add n/James p/12345678` would create a contact named James with a phone number 12345678.
* `add n/James e/james@example.com ig/itsjameshere b/01/01/2000` would create a contact named James with an email james@example.com, an Instagram of `itsjameshere` and a birthday of January 1st, 2000.
* `add n/James tg/itsjameshere mod/CS2103T mod/CS2101 cca/NUS Hackers` would create a contact named James with Telegram `itsjameshere`, the module tags of CS2103T and CS2101, and the CCA of NUS Hackers.

Please refer to **[this section](#additional-information-regarding-specific-fields)** for details on how to use each information field prefix.

### Listing all persons : `list`

Shows a list of all persons in the ConnectUS app.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the ConnectUS app.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [tg/TELEGRAM] [ig/INSTAGRAM] [wa/WHATSAPP] [b/BIRTHDAY] [mod/MODULE_NUMBER]…​ [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** e.g. 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* You can remove all the person’s tags by typing `edit -t` without specifying any tags after it.

Examples:
* `edit 1 p/12345678 e/james@example.com` Edits the phone number and email address of the 1st person to be `12345678` and `james@example.com` respectively.
* `edit 2 n/Betsy Crower -t` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

Please refer to **[this section](#additional-information-regarding-specific-fields)** for details on how to use each information field prefix.

### Deleting a person : `delete`

Deletes the specified person from the ConnectUS app.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** e.g. 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the ConnectUS app.
* `search Betsy` followed by `delete 1` deletes the 1st person in the results of the `search` command.

### Searching for personal information : `search`

Finds persons whose information fields and tags contain any of the given keywords.

Format: `search KEYWORD`

Examples:
* `search january` returns all persons whose information fields contain the keyword `january`.
* `search alex may` returns all persons whose information fields contain the keywords `alex` and `may`.

* The keywords are case-insensitive! This means that `search january`, `search JANUARY` and `search jAnUaRy` will all return the persons whose information fields contain the keyword `january`.

### Clearing all entries : `clear`

Clears all entries from the ConnectUS app.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ConnectUS data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ConnectUS data is saved as a JSON file `[JAR file location]/data/ConnectUS.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ConnectUS will discard all data and start with an empty data file at the next run.
</div>

### Additional information regarding specific fields

**Name:**
* Name is a *compulsory* field, i.e. a contact cannot exist if it does not have a name.
* The prefix for a name is `n/`.
* Names should only contain alphanumeric characters and spaces.

**Phone:**
* Phone is an *optional* field, i.e. a contact can exist even if it does not have a phone number.
* The prefix for a phone is `p/`.
* Phone numbers should only contain numeric characters.
* Phone numbers should be at least 3 digits long.

**Email**
* Email is an *optional* field, i.e. a contact can exist even if it does not have an email.
* The prefix for an email is `e/`.
* Emails should be of the format local-part@domain and adhere to the following constraints:
    * The local-part should only contain alphanumeric characters and these special characters:`+_.-`. The local-part may not start or end with any special characters.
    * This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods.
      The domain name must:
        - end with a domain label at least 2 characters long
        - have each domain label start and end with alphanumeric characters
        - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.

**Address**
* Address is an *optional* field, i.e. a contact can exist even if it does not have an address.
* The prefix for an address is `a/`.
* Addresses can take any values, including special characters.

**Instagram**
* Instagram is an *optional* field, i.e. a contact can exist even if it does not have an Instagram.
* The prefix for an Instagram username is `ig/`
* Instagram usernames should be of the format john.123.doe and adhere to the following constraints:
    * The username should only contain alphanumeric characters and the special character: `.`.
    * The dots `.` must not be consecutive or at the end.
    * The username should contain at most 30 characters.

**Telegram**
* Telegram is an *optional* field, i.e. a contact can exist even if it does not have a Telegram.
* Telegram usernames should be of the format johndoe and adhere to the following constraints:
    * The username should only contain alphanumeric characters and the special character `_`.
    * The username should contain at least 5 characters.

**WhatsApp**
* WhatsApp is an *optional* field, i.e. a contact can exist even if it does not have a WhatsApp.
* A WhatsApp's user identifier is a phone number, which should adhere to the following constraints:
    * Phone numbers should only contain numeric characters.
    * Phone numbers should be at least 3 digits long.

**Birthday**
* Birthday is an *optional* field, i.e. a contact can exist even if it does not have a birthday.
* Birthdays should be of the format DD/MM/YYYY:
  * `01/01/2000` would correspond to January 1st, 2000
  * `16/05/1990` would correspond to May 16th, 1990.

**Module**
* Module is an *optional* field, i.e. a contact can exist even if it does not have a module.
* Module names should be alphanumeric.
* A person can have any number of modules assigned to it.

**Tag**
* Tag is an *optional* field, i.e. a contact can exist even if it does not have a tag.
* Tag names should be alphanumeric.
* A person can have any number of tags assigned to it.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ConnectUS home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format                                                                                                                                                                       | Examples                                                                               |
|------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [tg/TELEGRAM] [ig/INSTAGRAM] [wa/WHATSAPP] [b/BIRTHDAY] [mod/MODULE_NUMBER]…​`                                            | `add n/James p/12345678 e/james@example.com tg/@itsjameshere b/14/02/2000 mod/CS2103T` |
| **Clear**  | `clear`                                                                                                                                                                      |                                                                                        |
| **Delete** | `delete INDEX`                                                                                                                                                               | `delete 3`                                                                             |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [tg/TELEGRAM] [ig/INSTAGRAM] [wa/WHATSAPP] [b/BIRTHDAY] [mod/MODULE_NUMBER]…​ [cca/CCA]…​ [ccap/CCA: POST]…​ -t` | `edit 1 p/12345678 e/james@example.com tg/@itsjameshere`                               |
| **List**   | `list`                                                                                                                                                                       |                                                                                        |
| **Help**   | `help`                                                                                                                                                                       |                                                                                        |
| **Search** | `search KEYWORD`                                                                                                                                                             | `search alex january`                                                                  |
| **Exit**   | `exit`                                                                                                                                                                       |                                                                                        |
