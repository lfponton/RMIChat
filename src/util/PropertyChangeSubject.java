package util;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject
{
  public void addPropertyChangeListener(String name, PropertyChangeListener listener);
  public void addPropertyChangeListener(PropertyChangeListener listener);
}
