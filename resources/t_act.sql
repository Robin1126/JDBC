drop table if exists t_act;

/*==============================================================*/
/* Table: t_act                                                 */
/*==============================================================*/
create table t_act
(
   actno                bigint not null,
   balance              double(7,2),
   primary key (actno)
);
INSERT INTO t_act(actno,balance) VALUES(111,20000);
INSERT INTO t_act(actno,balance) VALUES(222,0);