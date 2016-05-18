package temp;

import java.util.ArrayList;

import record.dto.PlayerDTO;
import record.service.RecordService;
import record.service.RecordServiceImpl;

public class Timer {

	public static void main(String[] args) {

	
		String eventId = "20160401HHLG02016";
		
		RecordService service = new RecordServiceImpl();

		ArrayList<PlayerDTO> list = new ArrayList<PlayerDTO>();
		PlayerDTO dto = new PlayerDTO();
		
		list = service.playerRecordData(eventId);
		
		int size = list.size();
		for (int z = 0; z < size; z++) {
			System.out.println(list.get(z).toString());
		}
		
	}

}
