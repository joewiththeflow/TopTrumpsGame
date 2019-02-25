package commandline;

/**
 * A definition of symbols used for command line output.
 */
enum CharCodes {
   // Box elements
   HORIZONTAL ("\u2500"),
   TOP_LEFT ("\u250C"),
   TOP_RIGHT ("\u2510"),
   VERTICAL ("\u2502"),
   TEE_RIGHT ("\u251C"),
   TEE_LEFT ("\u2524"),
   BOTTOM_LEFT ("\u2514"),
   BOTTOM_RIGHT ("\u2518"),
   TEE_DOWN ("\u252C"),
   TEE_UP ("\u2534"),

   // Misc Symbols
   PLAYER_ACTIVE ("[A]"),
   PLAYER_WAITING ("[ ]"),
   PLAYER_OUT_GAME ("[X]"),
   ACTIVE_CATEGORY_LEFT ("["),
   ACTIVE_CATEGORY_RIGHT ("]"),
   ROUND_WINNER ("[WIN!]");

   String code;

   CharCodes(String code) {
      this.code = code;
   }

   /**
    * Gets the code value.
    *
    * @return the code value.
    */
   String getCode() {
      return code;
   }
}