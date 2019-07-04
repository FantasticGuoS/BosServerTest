CREATE OR REPLACE TRIGGER "BI_M_OUT_MSCODEITEM" 
INSTEAD OF INSERT  OR DELETE ON m_out_mscodeitem
 REFERENCES OLD AS OLD NEW AS NEW
 FOR EACH ROW
 
--add by xusc 2012-4-1  ��Ψһ��ɨ��
--History:
    -- 1. Date:2013-06-18
    -- Author:tdd
    -- Modification: �����߼��жϣ������۵�or���������Ƿ���ΪY���������������޸ĳ��ⵥ��ϸ
	-- 2. Date:2013-07-04
    -- Author:tdd
    -- Modification:���ӿ��ƣ�������ⵥ�Ĳ��װ��Ϊ2���������������޸���ϸ��
    -- 3. Date:2016-10-10
    -- Author:XKK
    -- Modification:�޸��߼���
    --���Ϊɾ����ͨ����ϸ��idɾ�����ݣ�����ͨ��Ψһ�룬��ΪΨһ�����Ϊ��(ԭ�߼���ͨ��Ψһ��ɾ��)��
    --���Ϊ���������޸ĺ���߼�Ϊ��
    --�ж������[Ψһ��/����]�Ƿ�Ϊ���룬���Ϊ������
    --���ӿ��ƣ��������������Ӧ�Ŀ�ŵ�[�Ƿ�ʹ��Ψһ��]ΪY������ʾ����ţ�XXXX����ʹ��Ψһ�룬��ȷ�ϡ�
    --�������������Ѿ����ڣ��������ϲ����Ѿ����ڵļ�¼�ϡ�
    --�����������벻���ڣ�������һ����¼��
    --Ψһ��/����Ϊ�ա�
    --���롢��Ʒ��ASIΪ�������Ӧ��ֵ��
    --����Ϊ��ǰ������
    --��������[Ψһ��/����]�������룬��[Ψһ��/����]������Ƿ�ΪΨһ�루����ϵͳ����[portal.6001]��ȡ��������Զ�Ӧ�����ǵ����룬����Ϊ�����ΪΨһ�룩�����ΪΨһ�룬��
    --���ӿ��ƣ���������Ψһ���Ӧ�����������Ŀ�ŵ�[�Ƿ�ʹ��Ψһ��]ΪN������ʾ����ţ�XXXX��ʹ��Ψһ�룬��ȷ�ϡ�
    --���ӿ��ƣ����������Ϊ1��-1��������
    --���ӿ��ƣ���������Ψһ���Ѿ����ڣ�������
    --����һ����¼��
    --Ψһ��/����Ϊ��ǰֵ��
    --���롢��Ʒ��ASIΪ�������Ӧ��ֵ��
    --����Ϊ��ǰ������
    --���[Ψһ��/����]����Ĳ�������Ҳ����Ψһ�룬����ʾ�������Ψһ��/�����������������롣
    --���Ϊ���£���
    --���[Ψһ��/����]��Ϊ�գ��޸ĺ��������Ϊ1��-1��������
    --���[Ψһ��/����]Ϊ�գ��������޸�������
    --���ӿ��ƣ�[Ψһ��/����]Ϊ�գ��޸ĺ������С��0��������
    --4.Author:XKK
    --Date:20170228
    --Modification:���ⵥ��Ψһ����ϸ���棬ʹ��portal.6001����֧�ֶ���Ψһ���ȡλ��

DECLARE
		v_id                     NUMBER(10);
		v_billtype               VARCHAR2(80);
		v_count                  NUMBER(10);
		v_cnt                    NUMBER(10);
		v_productid              NUMBER(10);
		v_attributesetinstanceid NUMBER(10);
		v_mastercode             VARCHAR2(255) := :NEW.mastercode;
		v_no                     VARCHAR2(255);
		v_pdaid                  NUMBER(10);
		v_flownum                NUMBER(10);
		v_is_bas                 m_out.is_bas%TYPE; --�Ƿ���

BEGIN

		/*SELECT substr(:NEW.m_out_id, 1, length(:NEW.m_out_id) - 2),
           chr(substr(:NEW.m_out_id, -2))
    INTO v_id, v_dype
    FROM dual;
    
    SELECT decode(v_dype, 'A', 'M_SALEOUT', 'B', 'M_RET_SALEOUT', 'C',
                   'M_TRANSFEROUT', 'D', 'M_V_CUSRET_PUROUT', 'E', 'M_RET_PUROUT')
    INTO v_billtype1
    FROM dual;*/

		--ɾ����ϸ
		IF deleting THEN
				SELECT substr(:OLD.m_out_id, 1, length(:OLD.m_out_id) - 2),
							 chr(substr(:OLD.m_out_id, -2))
					INTO v_id, v_billtype
					FROM dual;
		
				--��ȡ��Ӧ���ⵥ���Ƿ���
				SELECT m.is_bas INTO v_is_bas FROM m_out m WHERE m.id = :OLD.m_out_id;
		
				--add by tdd  2013-06-18
				--�����߼��жϣ������۵�or���������Ƿ���ΪY���������������޸ĳ��ⵥ��ϸ
				IF v_is_bas = 'Y' AND v_billtype IN ('A', 'C', 'E') THEN
						raise_application_error(-20201,
																		'�ó��ⵥ���Ƿ���Ϊ�ǣ��������������޸ġ�ɾ����ϸ��');
				END IF;
				--end tdd 2013-06-18
		
				CASE
						WHEN v_billtype = 'A' THEN
								DELETE FROM m_sale_mscodeitem t
								 WHERE t.m_sale_id = v_id
									 AND t.mastercode = :OLD.mastercode;
						WHEN v_billtype = 'B' THEN
								DELETE FROM m_retsale_mscodeitem t
								 WHERE t.m_ret_sale_id = v_id
									 AND t.mastercode = :OLD.mastercode;
						WHEN v_billtype = 'C' THEN
								DELETE FROM m_tran_mscodeitem t
								 WHERE t.m_transfer_id = v_id
									 AND t.mastercode = :OLD.mastercode;
						WHEN v_billtype = 'D' THEN
								DELETE FROM m_retsale_mscodeitem t
								 WHERE t.m_ret_sale_id = v_id
									 AND t.mastercode = :OLD.mastercode;
						WHEN v_billtype = 'E' THEN
								DELETE FROM m_retpur_mscodeitem t
								 WHERE t.m_ret_pur_id = v_id
									 AND t.mastercode = :OLD.mastercode;
				END CASE;
		
		END IF;
		IF inserting THEN
				SELECT substr(:NEW.m_out_id, 1, length(:NEW.m_out_id) - 2),
							 chr(substr(:NEW.m_out_id, -2))
					INTO v_id, v_billtype
					FROM dual;
		
				--��ȡ��Ӧ���ⵥ���Ƿ���
				SELECT m.is_bas INTO v_is_bas FROM m_out m WHERE m.id = :NEW.m_out_id;
		
				--add by tdd  2013-06-18
				--�����߼��жϣ������۵�or���������Ƿ���ΪY���������������޸ĳ��ⵥ��ϸ
				IF v_is_bas = 'Y' AND v_billtype IN ('A', 'C', 'E') THEN
						raise_application_error(-20201,
																		'�ó��ⵥ���Ƿ���Ϊ�ǣ��������������޸ġ�ɾ����ϸ��');
				END IF;
				--end tdd 2013-06-18
				--����������Ƿ����
				SELECT COUNT(1)
					INTO v_count
					FROM m_out_mscodeitem
				 WHERE mastercode = v_mastercode
					 AND m_out_id = :NEW.m_out_id;
		
				IF v_count <> 0 THEN
						raise_application_error(-20202,
																		'�����Ψһ���ڱ����Ѵ��ڣ�' || v_mastercode || '��');
				END IF;
		
				--��һ������ʱ���ж�
				SELECT COUNT(*)
					INTO v_count
					FROM m_out_mscodeitem
				 WHERE m_out_id = :NEW.m_out_id;
		
				IF v_count = 0 THEN
						--�жϳ��ⵥ��ϸ���Ƿ���ڳ�����������0
						SELECT COUNT(1)
							INTO v_cnt
							FROM m_outitem
						 WHERE m_out_id = :NEW.m_out_id
							 AND qtyout <> 0;
				
						IF v_cnt <> 0 THEN
								raise_application_error(-20201,
																				'���ڳ��ⵥ��ϸ����������Ϊ0����������Ψһ��ɨ���¼��');
						END IF;
				END IF;
		
				--��ȡ��ǰϵͳʹ��Ψһ����ˮλ��
				SELECT t.VALUE INTO v_flownum FROM ad_param t WHERE t.NAME = 'portal.6001';
		
				--��֤Ψһ���Ƿ�Ϸ�
				SELECT COUNT(1)
					INTO v_cnt
					FROM m_product_alias t
				 WHERE t.no = substr(v_mastercode, 1, length(v_mastercode) - v_flownum);
		
				IF v_cnt = 0 THEN
						raise_application_error(-20001, '�޷�ʶ��Ψһ�룺' || v_mastercode || '��');
				END IF;
		
				SELECT t.id, t.no, t.m_product_id, t.m_attributesetinstance_id
					INTO v_pdaid, v_no, v_productid, v_attributesetinstanceid
					FROM m_product_alias t
				 WHERE t.no = substr(v_mastercode, 1, length(v_mastercode) - v_flownum);
		
				CASE
				
						WHEN v_billtype = 'A' THEN
								SELECT COUNT(*)
									INTO v_count
									FROM m_saleitem t
								 WHERE t.m_product_id = v_productid
									 AND t.m_attributesetinstance_id = v_attributesetinstanceid
									 AND t.m_sale_id = v_id;
						
								IF v_count = 0 THEN
										raise_application_error(-20001,
																						'���룺' || v_no || ',�ڵ�����ϸ�ﲻ���ڣ�');
								END IF;
						
								INSERT INTO m_sale_mscodeitem
										(id, ad_client_id, ad_org_id, m_sale_id, mastercode,
										 m_productalias_id, m_product_id, m_attributesetinstance_id, ownerid,
										 modifierid, creationdate, modifieddate, isactive)
										SELECT get_sequences('m_sale_mscodeitem'), t.ad_client_id, t.ad_org_id,
													 v_id, v_mastercode, v_pdaid, v_productid,
													 v_attributesetinstanceid, :NEW.ownerid, :NEW.modifierid,
													 SYSDATE, SYSDATE, 'Y'
											FROM m_sale t
										 WHERE rownum = 1;
						
						WHEN v_billtype = 'B' THEN
						
								SELECT COUNT(*)
									INTO v_count
									FROM m_ret_saleitem t
								 WHERE t.m_product_id = v_productid
									 AND t.m_attributesetinstance_id = v_attributesetinstanceid
									 AND t.m_ret_sale_id = v_id;
						
								IF v_count = 0 THEN
										raise_application_error(-20001,
																						'���룺' || v_no || ',�ڵ�����ϸ�ﲻ���ڣ�');
								END IF;
						
								INSERT INTO m_retsale_mscodeitem
										(id, ad_client_id, ad_org_id, m_ret_sale_id, mastercode,
										 m_productalias_id, m_product_id, m_attributesetinstance_id, ownerid,
										 modifierid, creationdate, modifieddate, isactive)
										SELECT get_sequences('m_retsale_mscodeitem'), t.ad_client_id,
													 t.ad_org_id, v_id, v_mastercode, v_pdaid, v_productid,
													 v_attributesetinstanceid, :NEW.ownerid, :NEW.modifierid,
													 SYSDATE, SYSDATE, 'Y'
											FROM m_ret_sale t
										 WHERE rownum = 1;
						WHEN v_billtype = 'C' THEN
						
								SELECT COUNT(*)
									INTO v_count
									FROM m_transferitem t
								 WHERE t.m_product_id = v_productid
									 AND t.m_attributesetinstance_id = v_attributesetinstanceid
									 AND t.m_transfer_id = v_id;
						
								IF v_count = 0 THEN
										raise_application_error(-20001,
																						'���룺' || v_no || ',�ڵ�����ϸ�ﲻ���ڣ�');
								END IF;
						
								INSERT INTO m_tran_mscodeitem
										(id, ad_client_id, ad_org_id, m_transfer_id, mastercode,
										 m_productalias_id, m_product_id, m_attributesetinstance_id, ownerid,
										 modifierid, creationdate, modifieddate, isactive)
										SELECT get_sequences('m_tran_mscodeitem'), t.ad_client_id, t.ad_org_id,
													 v_id, v_mastercode, v_pdaid, v_productid,
													 v_attributesetinstanceid, :NEW.ownerid, :NEW.modifierid,
													 SYSDATE, SYSDATE, 'Y'
											FROM m_transfer t
										 WHERE rownum = 1;
						WHEN v_billtype = 'D' THEN
						
								SELECT COUNT(*)
									INTO v_count
									FROM m_ret_saleitem t
								 WHERE t.m_product_id = v_productid
									 AND t.m_attributesetinstance_id = v_attributesetinstanceid
									 AND t.m_ret_sale_id = v_id;
						
								IF v_count = 0 THEN
										raise_application_error(-20001,
																						'���룺' || v_no || ',�ڵ�����ϸ�ﲻ���ڣ�');
								END IF;
						
								INSERT INTO m_retsale_mscodeitem
										(id, ad_client_id, ad_org_id, m_ret_sale_id, mastercode,
										 m_productalias_id, m_product_id, m_attributesetinstance_id, ownerid,
										 modifierid, creationdate, modifieddate, isactive)
										SELECT get_sequences('m_retsale_mscodeitem'), t.ad_client_id,
													 t.ad_org_id, v_id, v_mastercode, v_pdaid, v_productid,
													 v_attributesetinstanceid, :NEW.ownerid, :NEW.modifierid,
													 SYSDATE, SYSDATE, 'Y'
											FROM m_ret_sale t
										 WHERE rownum = 1;
						WHEN v_billtype = 'E' THEN
						
								SELECT COUNT(*)
									INTO v_count
									FROM m_ret_puritem t
								 WHERE t.m_product_id = v_productid
									 AND t.m_attributesetinstance_id = v_attributesetinstanceid
									 AND t.m_ret_pur_id = v_id;
						
								IF v_count = 0 THEN
										raise_application_error(-20001,
																						'���룺' || v_no || ',�ڵ�����ϸ�ﲻ���ڣ�');
								END IF;
						
								INSERT INTO m_retpur_mscodeitem
										(id, ad_client_id, ad_org_id, m_ret_pur_id, mastercode,
										 m_productalias_id, m_product_id, m_attributesetinstance_id, ownerid,
										 modifierid, creationdate, modifieddate, isactive)
										SELECT get_sequences('m_retpur_mscodeitem'), t.ad_client_id,
													 t.ad_org_id, v_id, v_mastercode, v_pdaid, v_productid,
													 v_attributesetinstanceid, :NEW.ownerid, :NEW.modifierid,
													 SYSDATE, SYSDATE, 'Y'
											FROM m_ret_pur t
										 WHERE rownum = 1;
						
				END CASE;
		
		END IF;
END;
/