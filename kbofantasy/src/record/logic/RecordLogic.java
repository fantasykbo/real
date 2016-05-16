package record.logic;

import java.io.IOException;

public interface RecordLogic {
	// ���ΰ���� ũ�Ѹ� ����
	String eventRecordData(String eventId) throws IOException;
	// ���ð��������� ũ�Ѹ� ����
	String eventTodayData(String year, String month, String day) throws IOException;
	
	// �Ѵ�ġ ��� ũ�Ѹ�(�ӽ�, ������ ������)
	String eventMonthData(String year, String month);
	
}