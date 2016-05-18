package record.logic;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import record.dto.PlayerDTO;

public interface RecordLogic {
	
// ��Ͻ� ������� ���� ����	
	// ���ΰ���� ũ�Ѹ� ����
	String eventRecordData(String eventId) throws IOException;
	// ���ð��������� ũ�Ѹ� ����
	String eventTodayData(String year, String month, String day) throws IOException;
	
	
	// ��Ͻ� ������� �Ľ� ����
	ArrayList<PlayerDTO> playerRecordData(String eventId, String eventRecordData) throws ParseException;
	
	

	
	
	
	// �Ѵ�ġ ��� ũ�Ѹ�(�ӽ�, ������ ������)
	String eventMonthData(String year, String month);

	
	
}