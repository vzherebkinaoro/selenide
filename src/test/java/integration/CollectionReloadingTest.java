package integration;

import com.codeborne.selenide.ElementsCollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

class CollectionReloadingTest extends IntegrationTest {
  @BeforeEach
  void openTestPage() {
    openFile("collection_with_delays.html");
  }

  @Test
  void reloadsCollectionOnEveryCall() {
    ElementsCollection collection = $$("#collection li");
    collection.get(0).shouldHave(text("Element #0"));
    collection.get(10).shouldHave(text("Element #10"));
  }

  @Test
  void canTakeSnapshotOfCollection() {
    ElementsCollection collection = $$("#collection li");
    ElementsCollection snapshot = collection.snapshot();
    int currentSize = snapshot.size();

    collection.shouldHave(sizeGreaterThan(currentSize));
    snapshot.shouldHave(size(currentSize));
  }
}
