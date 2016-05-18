package record.service;

import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import record.dto.PlayerDTO;
import record.dto.RecordDTO;


public interface RecordService {

	// ���ΰ���� ũ�Ѹ�(from Logic)
	String eventRecordData(String eventId);
	// ���ð��������� ũ�Ѹ�(from Logic)
	String eventTodayData(String year, String month, String day);
	
	// ��Ͻ� ������� �Ľ�(from Logic)
	ArrayList<PlayerDTO> playerRecordData(String eventId, String eventRecordData);

	
	// ���������� DB(from DAO(
	ArrayList<RecordDTO> eventList(String year, String month);
	
	
	
	// �Ѵ�ġ ��� ũ�Ѹ�(�ӽ�, ������ ������)
	String eventMonthData(String year, String month);

}
