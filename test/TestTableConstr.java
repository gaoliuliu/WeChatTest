





import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;



public class TestTableConstr  extends TestCase{
	private static SessionFactory sessionFactory;
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("set up");
		sessionFactory = new AnnotationConfiguration().configure()
				.buildSessionFactory();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("teardwon");
		sessionFactory.close();
	}
	public void saveInfo()
	{
		/*Session session = sessionFactory.getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {
			
			Module m = new Module();
			m.setModuleId("C0001");
			m.setIntro("°¸Àý1");
			m.setDetail("shifenlihiai");
			m.setStatus("1");
			String  strModule = "update tbl_tsk_info t set t.policyno = #policyno# where t.modno = #modno#;" +
					"\nupdate tbl_fail_tsk_info f set f.isresend = '1' where f.modno = #modno#;";
			
			m.setModule(strModule);
			
			session.save(m);
			
			ModuleInput i1= new ModuleInput();
			i1.setModuleId("C0001");
			i1.setRegex("15-18");
			i1.setInput("policyno");
			
			ModuleInput i2= new ModuleInput();
			i2.setModuleId("C0001");
			i2.setRegex("15-18");
			i2.setInput("modno");
			
			session.save(i1);
			session.save(i2);
			

			tx.commit();*/
/*		} catch (Exception ex) {
			tx.rollback();
			ex.printStackTrace();
		}
*/
	}
	public void testSchemaExport()
	{
		SchemaExport se = new SchemaExport(new AnnotationConfiguration().configure());
		se.create(true, true);
	}
}
