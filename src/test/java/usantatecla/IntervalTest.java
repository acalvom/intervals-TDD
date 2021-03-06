package usantatecla;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntervalTest {
  
  private Point left = new Point(-2.2);
  private Point right = new Point(4.4);
  private IntervalBuilder intervalBuilder;
  private IntervalBuilder intervalBuilder2;

  @BeforeEach
  public void before(){
    this.left = new Point(-2.2);
    this.right = new Point(4.4);
    this.intervalBuilder = new IntervalBuilder();
    this.intervalBuilder2 = new IntervalBuilder();
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWithIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));
    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenInc3ludeWithIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWit3hIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWithInclude5dValueThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervalWhenIntersectWithIntervalWithinThenTrue() {
    Interval interval1 = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    Interval interval2 = this.intervalBuilder2.closed(left.getGreater()).closed(right.getLess()).build();
    assertTrue(interval1.intersect(interval2));
  }

  @Test
  public void givenIntervalWhenIntersectWithRightIntervalOutsideThenFalse() {
    Interval intervalLeft = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    Interval intervalRight = this.intervalBuilder2.closed(right.getGreater()).closed(right.getGreater()).build();
    assertFalse(intervalLeft.intersect(intervalRight));
  }

  @Test
  public void givenIntervalWhenIntersectWithRightIntervalPartiallyWithinThenTrue() {
    Interval intervalLeft = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    Interval intervalRight = this.intervalBuilder2.closed(left.getGreater()).closed(right.getGreater()).build();
    assertTrue(intervalLeft.intersect(intervalRight));
  }

  @Test
  public void givenIntervalWhenIntersectWithRightIntervalLimitValueThenTrue() {
    Interval intervalLeft = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    Interval intervalRight = this.intervalBuilder2.closed(right.getEquals()).closed(right.getGreater()).build();
    assertTrue(intervalLeft.intersect(intervalRight));
  }

  @Test
  public void givenIntervalClosedWhenIntersectWithRightIntervalOpenLimitValueThenFalse() {
    Interval intervalLeft = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    Interval intervalRight = this.intervalBuilder2.open(right.getEquals()).closed(right.getGreater()).build();
    assertFalse(intervalLeft.intersect(intervalRight));
  }

  @Test
  public void givenIntervalOpenWhenIntersectWithRightIntervalClosedLimitValueThenFalse() {
    Interval intervalLeft = this.intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
    Interval intervalRight = this.intervalBuilder2.closed(right.getEquals()).closed(right.getGreater()).build();
    assertFalse(intervalLeft.intersect(intervalRight));
  }

  @Test
  public void givenIntervalOpenWhenIntersectWithRightIntervalOpenLimitValueThenFalse() {
    Interval intervalLeft = this.intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
    Interval intervalRight = this.intervalBuilder2.open(right.getEquals()).closed(right.getGreater()).build();
    assertFalse(intervalLeft.intersect(intervalRight));
  }

  @Test
  public void givenIntervalWhenIntersectWithLeftIntervalOutsideThenFalse() {
    Interval intervalRight = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    Interval intervalLeft = this.intervalBuilder2.closed(left.getLess()).closed(left.getLess()).build();
    assertFalse(intervalRight.intersect(intervalLeft));
  }

  @Test
  public void givenIntervalWhenIntersectWithLeftIntervalPartiallyWithinThenTrue() {
    Interval intervalRight = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    Interval intervalLeft = this.intervalBuilder2.closed(left.getLess()).closed(right.getEquals()).build();
    assertTrue(intervalRight.intersect(intervalLeft));
  }

  @Test
  public void givenIntervalWhenIntersectWithLeftIntervalLimitValueThenTrue() {
    Interval intervalRight = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    Interval intervalLeft = this.intervalBuilder2.closed(left.getLess()).closed(left.getEquals()).build();
    assertTrue(intervalRight.intersect(intervalLeft));
  }

  @Test
  public void givenIntervalOpenWhenIntersectWithLeftIntervalClosedLimitValueThenFalse() {
    Interval intervalRight = this.intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
    Interval intervalLeft = this.intervalBuilder2.closed(left.getLess()).closed(left.getEquals()).build();
    assertFalse(intervalRight.intersect(intervalLeft));
  }

  @Test
  public void givenIntervalClosedWhenIntersectWithLeftIntervalOpenLimitValueThenFalse() {
    Interval intervalRight = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    Interval intervalLeft = this.intervalBuilder2.closed(left.getLess()).open(left.getEquals()).build();
    assertFalse(intervalRight.intersect(intervalLeft));
  }

  @Test
  public void givenIntervalOpenWhenIntersectWithLeftIntervalOpenLimitValueThenFalse() {
    Interval intervalRight = this.intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
    Interval intervalLeft = this.intervalBuilder2.closed(left.getLess()).open(left.getEquals()).build();
    assertFalse(intervalRight.intersect(intervalLeft));
  }

}