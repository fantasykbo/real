package record.dao;

import game.dto.MyTeamDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import record.dto.BatterDTO;
import record.dto.EventDTO;
import record.dto.PitcherDTO;
import record.dto.PlayerDTO;

public interface RecordDAO {
	// ���������� DB ȣ��
	ArrayList<EventDTO> eventList(String year, String month, Connection con) throws SQLException;
	// �� ������ ���� ����� DB ȣ��
	ArrayList<EventDTO> teamTable(Connection con) throws SQLException;

	// �������� DB ȣ��
	PlayerDTO playerInfo(String playerCode, Connection con) throws SQLException;
	// Ÿ�� ���� DB ȣ��
	BatterDTO batterStat(String playerCode, Connection con) throws SQLException;
	// Ÿ�� �ֱ� 10��� ���� DB ȣ��
	ArrayList<BatterDTO> batterLastStat(String playerCode, Connection con) throws SQLException;
	// ���� ���� DB ȣ��
	PitcherDTO pitcherStat(String playerCode, Connection con) throws SQLException;
	// ���� �ֱ� 10��� ���� DB ȣ��
	ArrayList<PitcherDTO> pitcherLastStat(String playerCode, Connection con) throws SQLException;
	
	// ���� ��� ��� DB Insert
	int dailyRecord(ArrayList<EventDTO> list, Connection con) throws SQLException;
	// ���� ��� ��� DB Insert
	String batterRecord(ArrayList<BatterDTO> list, Connection con) throws SQLException;
	String pitcherRecord(ArrayList<PitcherDTO> list, Connection con) throws SQLException;
	// ���� ��� ��� ����Ʈ �� ���� DB update
	int gamePoint(ArrayList<MyTeamDTO> list, Connection con) throws SQLException;
	// �� ���� ����Ʈ ���� ���� update
	int memberPoint(Connection con) throws SQLException;

	
}