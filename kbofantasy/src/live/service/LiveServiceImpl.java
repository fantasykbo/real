package live.service;

import live.logic.LiveLogic;
import live.logic.LiveLogicImpl;

public class LiveServiceImpl implements LiveService {

	@Override
	public String printlivetext(String eventId) {
		String str;
		LiveLogic logic = new LiveLogicImpl();
		str = logic.printlivetext(eventId);
		return str;
	}
	
}
