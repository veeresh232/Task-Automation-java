package com.quinnox.dbauto.constant;

public class Constant {
	
	public static final String queryToCheck="select * from (\r\n" + 
			"with vs as (select rownum rnum,\r\n" + 
			"                                                  							  inst_id,\r\n" + 
			"                                                                              sid,\r\n" + 
			"                                                                              serial#,\r\n" + 
			"                                                                              status,\r\n" + 
			"                                                                              username,\r\n" + 
			"                                                                              last_call_et,\r\n" + 
			"                                                                              command,\r\n" + 
			"                                                                              machine,\r\n" + 
			"                                                                              osuser,\r\n" + 
			"                                                                              module,\r\n" + 
			"                                                                              action,\r\n" + 
			"                                                                              resource_consumer_group,\r\n" + 
			"                                                                              client_info,\r\n" + 
			"                                                                              client_identifier,\r\n" + 
			"                                                                              type,\r\n" + 
			"                                                                              terminal,\r\n" + 
			"                                                                              sql_id,\r\n" + 
			"                                                                              sql_child_number\r\n" + 
			"                                                                         from gv$session) \r\n" + 
			"                                                             select vs.inst_id, vs.sid ,serial# serial, vs.sql_id, vs.sql_child_number,\r\n" + 
			"                                                                    vs.username \"Username\",\r\n" + 
			"                                                                    case when vs.status = 'ACTIVE' \r\n" + 
			"                                                                              then last_call_et \r\n" + 
			"                                                                         else null end \"Seconds in Wait\",\r\n" + 
			"                                                                    (select command_name from v$sqlcommand where command_type = vs.command ) \"Command\",\r\n" + 
			"                                                                    vs.machine \"Machine\",\r\n" + 
			"                                                                    vs.osuser \"OS User\", \r\n" + 
			"                                                                    lower(vs.status) \"Status\",\r\n" + 
			"                                                                    vs.module \"Module\",\r\n" + 
			"                                                                    vs.action \"Action\",\r\n" + 
			"                                                                    vs.resource_consumer_group,\r\n" + 
			"                                                                    vs.client_info,\r\n" + 
			"                                                                    vs.client_identifier\r\n" + 
			"                                                               from vs\r\n" + 
			"                                                              where vs.USERNAME is not null\r\n" + 
			"                                                              and vs.status='ACTIVE' \r\n" + 
			"                                                              and last_call_et>3600\r\n" + 
			"                                                                \r\n" + 
			"                                                                \r\n" + 
			"                                                                and nvl(vs.osuser,'x') <> 'SYSTEM'\r\n" + 
			"                                                                and vs.type <> 'BACKGROUND'\r\n" + 
			"                                                                order by 1,2,3\r\n" + 
			" ) sub1 order by 7 asc";
	public static final String queryToSqltext="select replace(q.sql_fulltext,chr(0))sql_text from gv$session s,gv$sql q where s.sql_address = q.address and s.sql_hash_value=q.hash_value and s.sid=?";

}
