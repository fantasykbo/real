package record.service;

import java.util.ArrayList;

import record.dto.RecordDTO;


public interface RecordService {

	// ���ΰ���� ũ�Ѹ�
	String eventRecordData(String eventId);
	// ���ð��������� ũ�Ѹ�
	String eventTodayData(String year, String month, String day);
	// ���������� DB
	ArrayList<RecordDTO> eventList(String year, String month);
	
	// �Ѵ�ġ ��� ũ�Ѹ�(�ӽ�, ������ ������)
	String eventMonthData(String year, String month);

}
