
## 介绍
base-crud是一个通用的基于mybatis、tkmapper的一个数据库增删改查的工具模块，你可以直接在你的项目中去使用；
## 好处
- 1、直接在自己的业务Api实现ServiceApi和在业务Impl中继承ServiceHelper即可实现不写一句ServiceImpl代码对所有单表进行CRUD操作；

- 2、基于tkMapper进行封装，稳定、成熟、可靠

- 3、灵活扩展，可自己进行适配，重写

- 4、从Controller进来的DTO参数对象开始，到DAO(CRUD)这个步骤是无感的，多数情况不需要手动进行参数转换，设定（视具体业务和表结构而定）、大大减少了重复的代码，有时候甚至不需要在service引入mapper就可以完成所有操作

- 5、模块里包含了springMVC的全局异常监控，入参出参的封装

##使用方式
- 1、简单
- 2、超级简单；

## 关键代码

- 1、将base-crud-common导入到你的项目模块中

- 2、在业务模块pom.xml里面引入下面这个依赖即可
    <dependency>
      <groupId>com.henson.basecrud</groupId>
      <artifactId>base-crud-common</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>
3、在你的业务接口实现ServiceApi

    public interface DemoService extends ServiceApi<DemoDTO> {
    }

4、在你的业务实现类实现

    @Service
    public class DemoServiceImpl extends 
    ServiceHelper<Demo, DemoDTO> implements DemoService {
    }

5、使用示列

    public class DemoServiceTest {

    @Autowired
    DemoService demoService;

    @Ignore
    @Test
    public void save() {
        DemoDTO demo = new DemoDTO();
        demo.setName("jack");
        demo.setId(4);
        demoService.save(demo);
    }

    @Ignore
    @Test
    public void update() {
        DemoDTO demo = new DemoDTO();
        demo.setName("jack4");
        demo.setId(4);
        demoService.update(demo);
    }

    @Ignore
    @Test
    public void get() {
        DemoDTO demo = new DemoDTO();
        demo.setId(1);
        System.out.println(demoService.get(demo).getName());
    }


    @Test
    public void query() {
        DemoDTO demo = new DemoDTO();
        demo.setName("aaa");
        System.out.println(JSON.toJSON(demoService.query(demo)));
    }

    @Ignore
    @Test
    public void search() {
        DemoDTO demo = new DemoDTO();
        demo.setName("jack");
        Search search = new Search();
        Operation oName = new Operation();
        oName.setFieldName("name");
        oName.setOp(OpEnum.EQ);
        oName.setValue("jack");
        List<Operation> operations = new ArrayList<>();
        operations.add(oName);
        search.setOperations(operations);
        System.out.println(JSON.toJSON(demoService.search(search)));
    }

    @Ignore
    @Test
    public void page() {
        DemoDTO demo = new DemoDTO();
        demo.setName("jack");
        System.out.println(JSON.toJSON(demoService.searchPage(1, 2, null, demo)));
    }

    @Ignore
    @Test
    public void delete() {
        DemoDTO demo = new DemoDTO();
        demo.setName("jack");
        demoService.delete(demo);
    }

}

# bse-crud
# base-spring-crud
