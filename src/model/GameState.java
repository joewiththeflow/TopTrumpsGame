package model;


/**
* Separate game states will be defined
* as different categories to be used in different
* ways for the game logic 
**/
public enum GameState {
      // A list of all possible game states.
      PLAYERS_SPAWNED,
      NEW_GAME_INITIALISED,
      NEW_ROUND_INITIALISED,
      CATEGORY_REQUIRED,
      CATEGORY_SELECTED,
      ROUND_COMPLETE,
   	HUMAN_OUT, ROUND_RESULT_COMPUTED
}
