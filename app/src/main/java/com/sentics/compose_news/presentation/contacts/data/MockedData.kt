import MockedData.contacts
import com.sentics.compose_news.presentation.contacts.data.Contact
import java.util.concurrent.ThreadLocalRandom

object MockedData {
    val contacts = generate500Contacts()
        .sortedBy { it.fullName }

    val randomContact get() = contacts[23]

    val letterIndexMap = generateLetterIndexMap()
}

private fun generate500Contacts(): List<Contact> {
    val contacts = mutableListOf<Contact>()
    val firstNames = listOf(
        "Alice", "Bob", "Charlie", "Diana", "Edward", "Fiona", "George", "Hannah", "Ian", "Jessica",
        "Kyle", "Liam", "Mia", "Noah", "Olivia", "Peter", "Quinn", "Rachel", "Steve", "Tina",
        "Ursula", "Victor", "Wendy", "Xavier", "Yara", "Zack", "Anna", "Ben", "Chloe", "David",
        "Ella", "Frank", "Grace", "Henry", "Ivy", "Jack", "Karen", "Leo", "Mona", "Nate",
        "Opal", "Paul", "Queenie", "Ryan", "Sara", "Tom", "Uma", "Vicky", "Will", "Xena"
    )
    val lastNames = listOf(
        "Smith", "Johnson", "Brown", "Prince", "Newgate", "Gallagher", "Washington", "Montana",
        "Somerhalder", "Alba", "Katarn", "Neeson", "Wallace", "Centineo", "Rodrigo", "Parker", "Quinn", "Green",
        "Rogers", "Turner", "Underwood", "Valdez", "White", "Xenon", "Young", "Zeller", "Anderson", "Baker", "Carter",
        "Davis", "Evans", "Fisher", "Gordon", "Hill", "Irving", "Jackson", "Kelly", "Lewis", "Miller", "Nelson",
        "Owens", "Perez", "Quigley", "Reed", "Scott", "Taylor", "Usher", "Vance", "Walker", "Ximenes", "Adams",
        "Brownfield", "Clark", "Davidson", "Edwards", "Foster", "Gonzales", "Hughes", "King", "Lee"
    )
    val domains = listOf("example.com", "mail.com", "mycontact.org", "personal.net", "corp.com", "biz.info")

    for (i in 1..500) {
        val id = i.toString()
        val firstName = firstNames[(i - 1) % firstNames.size]
        val lastName = lastNames[ThreadLocalRandom.current().nextInt(lastNames.size)]
        val avatar = "https://example.com/avatars/$id.png" // Dummy avatar URL
        val number =
            "+1-${String.format("%03d", (i % 100 + 100).coerceAtLeast(100))}-${String.format("%07d", i + 1000000)}"

        val email = if (ThreadLocalRandom.current().nextInt(0, 3) != 0) { // Approx 2/3 of contacts have an email
            "${firstName.lowercase()}.${lastName.lowercase().replace(" ", "")}@${
                domains[ThreadLocalRandom.current().nextInt(domains.size)]
            }"
        } else {
            null // Null email for some contacts
        }

        contacts.add(
            Contact(
                id = id,
                firstName = firstName,
                lastName = lastName,
                avatar = avatar,
                number = number,
                email = email
            )
        )
    }
    return contacts
}

private fun generateLetterIndexMap(): HashMap<String, Int> {
    val map = HashMap<String, Int>()
    var currentCharacter = ' '

    contacts.forEachIndexed { index, contact ->
        if (contact.firstName.first() != currentCharacter) {
            map.put(contact.firstName.first().toString(), index)
            currentCharacter = contact.firstName.first()
        }
    }

    return map
}