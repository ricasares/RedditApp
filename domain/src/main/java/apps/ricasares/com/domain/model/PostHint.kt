package apps.ricasares.com.domain.model

/**
 * Created by ricardo casarez on 3/29/18.
 */
enum class PostHint(val value: String) {
    LINK("link"),
    IMAGE("image"),
    SELF("self"),
    RICH_VIDEO("rich:video");

    companion object {
        fun valueFor(string: String) : PostHint? {
            for (hint in PostHint.values()) {
                if (hint.value == string) {
                    return hint
                }
            }
            return null
        }
    }
}