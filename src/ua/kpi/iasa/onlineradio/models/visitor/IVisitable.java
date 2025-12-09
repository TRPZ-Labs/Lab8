package ua.kpi.iasa.onlineradio.models.visitor;

public interface IVisitable {
    void accept(IVisitor visitor);
}