package record.logic;

import java.io.IOException;
import java.util.ArrayList;

import record.dto.RecordDTO;

public interface RecordLogic {
	// ���ΰ���� ũ�Ѹ� ����
	String eventRecordData(String eventId) throws IOException;
	// ���ð��������� ũ�Ѹ� ����
	String eventTodayData(String year, String month, String day) throws IOException;
}