create table E_THIRD_TIME(
  id                 NUMBER,
  type               VARCHAR2(80),
  propel_time        DATE default SYSDATE,
  remark             VARCHAR2(200),
  wechat_propel_time DATE default SYSDATE,
  brand              VARCHAR2(20)
);

alter table c_client_vip modify cardno varchar2(80);

alter table e_third_ticket add LIMIT_DISCOUNT NUMBER(16,4);
alter table e_third_ticket add LIMIT_PRICE NUMBER(16,4);
alter table e_third_ticket_his add LIMIT_DISCOUNT NUMBER(16,4);
alter table e_third_ticket_his add LIMIT_PRICE NUMBER(16,4);

comment on column e_third_ticket.LIMIT_DISCOUNT is '满足此折扣使用';
comment on column e_third_ticket.LIMIT_PRICE is '满足此金额使用';
comment on column e_third_ticket_his.LIMIT_DISCOUNT is '满足此折扣使用';
comment on column e_third_ticket_his.LIMIT_PRICE is '满足此金额使用';

insert into ad_param (ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATIONDATE, OWNERID, MODIFIEDDATE, MODIFIERID, NAME, DEFAULTVALUE, VALUE, VALUETYPE, PARMTYPE, VALUELIST, DESCRIPTION)
values (get_sequences('ad_param'), 37, 27, 'Y', to_date('18-09-2017 10:37:21', 'dd-mm-yyyy hh24:mi:ss'), 893, to_date('10-10-2017 20:16:53', 'dd-mm-yyyy hh24:mi:ss'), 893, 'portal.isshopping', 'false', 'false', 'B', '控制参数', 'true;false', '是否购物券');

