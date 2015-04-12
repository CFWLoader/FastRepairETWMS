package team.unnamed.fastrepair.dao.impl.test;

import org.junit.Test;
import team.unnamed.fastrepair.dao.ToolDao;
import team.unnamed.fastrepair.dao.impl.MySqlToolDao;
import team.unnamed.fastrepair.model.Tool;

import java.sql.SQLException;

/**
 * Created by cfwloader on 4/10/15.
 */
public class MySqlToolDaoTest {
    @Test
    public void testAddTool() throws SQLException {
        Tool tool = new Tool();

        /*
        tool.setToolType("Screwdriver");
        tool.setNumberOfAvailable(50);
        tool.setCompanyId(1);
        tool.setDepartmentId(1);
        */

        tool.setToolName("Pliers");
        tool.setIsExpensive(false);
        tool.setNumberOfAvailable(100);
        tool.setCompanyId(5);
        tool.setDepartmentId(4);

        ToolDao toolDao = new MySqlToolDao();

        toolDao.addTool(tool);

        toolDao.close();
    }
}
