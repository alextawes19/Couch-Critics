package com.example.demo.models;

public class User {
        /**
     * Unique identifier for the user.
     */
    private final String userId;

    /**
     * First name of the user.
     */
    private final String firstName;

    /**
     * Last name of the user.
     */
    private final String lastName;

    /**
     * Path of the profile image file for the user.
     */
    private final String profileImagePath;

    /**
     * Constructs a User with specified details.
     *
     * @param userId           the unique identifier of the user
     * @param firstName        the first name of the user
     * @param lastName         the last name of the user
     * @param profileImagePath the path of the profile image file for the user
     */
    public User(String userId, String firstName, String lastName, String profileImagePath) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImagePath = profileImagePath;
    }

    /**
     * Constructs a User with specified details.
     *
     * @param userId           the unique identifier of the user
     * @param firstName        the first name of the user
     * @param lastName         the last name of the user
     * @param profileImagePath the path of the profile image file for the user
     */
    public User(String userId, String firstName, String lastName) {
        this(userId, firstName, lastName, getAvatarPath(userId));
    }

    /**
     * Given a userId generate a valid avatar path.
     */
    private static String getAvatarPath(String userId) {
        int fileNo = (userId.hashCode() % 20) + 1;
        String avatarFileName = String.format("avatar_%d.png", fileNo);
        return "/avatars/" + avatarFileName;
    }

    /**
     * Returns the user ID.
     *
     * @return the user ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Returns the first name of the user.
     *
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the user.
     *
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the path of the profile image file for the user.
     *
     * @return the profile image path
     */
    public String getProfileImagePath() {
        return profileImagePath;
    }
}
