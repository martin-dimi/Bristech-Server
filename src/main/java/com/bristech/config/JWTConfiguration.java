package com.bristech.config;

public class JWTConfiguration {

    public static final String SECRET = "fbjkflsdkfjhguigbjtjk4214213blkujfbjlh34123jkb89y";
    public static final String HEADER = "token";
    public static final long TOKEN_EXP_DATE = (10 * JWTConfiguration.ONE_DAY);
    public static final String CLAIM_USER_ID = "userId";
    public static final String CLAIM_USER_NAME = "username";
    private static final long ONE_DAY = 86400000;

}
