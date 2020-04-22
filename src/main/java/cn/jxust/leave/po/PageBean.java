package cn.jxust.leave.po;

/**
 * @author tyeerth
 * @date 2019/11/11 - 10:23
 */

public class PageBean {

    private int page=1;       //哪一页
    private int pageSize=10;   //每页记录数
    private int start;         //起始页
    public PageBean() {
    }
    public PageBean(int page, int pageSize) {
        super();
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStart() {
        return (page-1)*pageSize;
    }


}
