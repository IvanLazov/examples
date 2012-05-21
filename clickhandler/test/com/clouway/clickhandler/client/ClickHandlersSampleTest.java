package com.clouway.clickhandler.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ClickHandlersSampleTest {

  interface PersonView {

    HasClickHandlers getSaveButton();

    String getPersonName();

  }

  class PersonPresenter {

    private PersonView personView;
    private final PersonService personService;

    public PersonPresenter(PersonService personService) {

      this.personService = personService;
    }

    public void bindDisplay(final PersonView personView) {

      personView.getSaveButton().addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          personService.savePerson(personView.getPersonName());
        }
      });

    }

  }

  interface PersonService {
    void savePerson(String name);
  }

  @Test
  public void personGetSavedWhenSaveButtonWasClicked() throws Exception {

    // GIVEN
    MockPersonService personService = new MockPersonService();
    PersonPresenter presenter = new PersonPresenter(personService);

    MockPersonView view = new MockPersonView();
    presenter.bindDisplay(view);


    // WHEN emulate button click
    view.saveButton.handler.onClick(new ClickEvent() { });


    // THEN
    assertThat(personService.savedPersons.size(), is(equalTo(1)));


  }

  class MockPersonService implements PersonService {

    public List<String> savedPersons = new ArrayList<String>();

    @Override
    public void savePerson(String name) {
      savedPersons.add(name);
    }
  }

  class MockPersonView implements PersonView {
    public String name;
    public MockClickHandler saveButton = new MockClickHandler();

    @Override
    public HasClickHandlers getSaveButton() {
      return saveButton;
    }

    @Override
    public String getPersonName() {
      return name;
    }
  }

  class MockClickHandler implements HasClickHandlers {

    public ClickHandler handler;

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
      this.handler = handler;
      return null;
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
    }
  }
}
