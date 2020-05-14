/**
 * Created by alexandru.dima1609 on 10-Jan-19.
 */
public interface Visitor
{
    public void visit(BookDepartment bookDepartment);
    public void visit(MusicDepartment musicDepartment);
    public void visit(SoftwareDepartment softwareDepartment);
    public void visit(VideoDepartment videoDepartment);

}
