package record.logic;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import record.dto.BatterDTO;
import record.dto.EventDTO;
import record.dto.PitcherDTO;

public interface RecordLogic {
	
// ��Ͻ� ������� ���� ����	
	// ���ΰ���� ũ�Ѹ� ����
	String eventRecordData(String eventId) throws IOException;
	// ���ð��������� ũ�Ѹ� ����
	String eventTodayData(String year, String month, String day) throws IOException;
	
// ��Ͻ� ������� �Ľ� ����
	// Ÿ�ڱ�� �Ľ� ����
	ArrayList<BatterDTO> batterRecordData(String eventId, String eventRecordData) throws ParseException;
	// ������� �Ľ� ����
	ArrayList<PitcherDTO> pitcherRecordData(String eventId, String eventRecordData) throws ParseException;

	// �� ���� ���̺� �Ľ� ����
	String teamTableData(ArrayList<EventDTO> list) throws ParseException;
	
	
	// ������� �Ľ� ����
//	ArrayList<RecordDTO> playerRecordData(String eventId, String eventRecordData) throws ParseException;
	
	
	
	//
	ArrayList<EventDTO> dailyRecordData(String eventTodayData) throws ParseException;
	
//	// �Ѵ�ġ ��� ũ�Ѹ�(�ӽ�, ������ ������ - ���Ϸ�)
//	String eventMonthData(String year, String month);

	
	
}