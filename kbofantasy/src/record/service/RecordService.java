package record.service;

import java.util.ArrayList;

import record.dto.BatterDTO;
import record.dto.EventDTO;
import record.dto.PitcherDTO;
import record.dto.PlayerDTO;

public interface RecordService {

	// ���ΰ���� ũ�Ѹ�(from Logic)
	String eventRecordData(String eventId);
	// ���ð��������� ũ�Ѹ�(from Logic)
	String eventTodayData(String year, String month, String day);
	
	// ��Ͻ� ������� �Ľ��ؼ� DB Insert
	String batterRecordData(String eventId);
	String pitcherRecordData(String eventId);
	
	// ���������� ���� �Ľ��ؼ� DB Update
	int dailyRecordData(String year, String month, String day);
	
	// ���������� DB���� �ҷ�����(from DAO)
	ArrayList<EventDTO> eventList(String year, String month);
	
	// �� ����
	String teamTableData();

	// �������� DB ȣ��
	PlayerDTO playerInfo(String playerCode);
	// Ÿ�� ���� DB ȣ��
	BatterDTO batterStat(String playerCode);
	// Ÿ�� �ֱ� 10��� ���� DB ȣ��
	ArrayList<BatterDTO> batterLastStat(String playerCode);
	// ���� ���� DB ȣ��
	PitcherDTO pitcherStat(String playerCode);
	// ���� �ֱ� 10��� ���� DB ȣ��
	ArrayList<PitcherDTO> pitcherLastStat(String playerCode);

}
