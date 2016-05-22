package game.service;

import game.dto.GamePlayerDTO;

import java.util.ArrayList;

public interface GameService {
	ArrayList<GamePlayerDTO> SelectMyList(int member_cd, String game_dt);
}
