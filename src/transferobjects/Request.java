package transferobjects;

import java.io.Serializable;

public class Request implements Serializable
{
  private String type;
  private Object argument;

  public Request(String type, Object argument)
  {
    this.type = type;
    this.argument = argument;
  }

  public String getType() {
    return type;
  }

  public Object getArgument()
  {
    return argument;
  }
}
