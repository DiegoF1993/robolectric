package com.xtremelabs.robolectric.shadows;

import android.graphics.Bitmap;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.WithTestDefaultsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(WithTestDefaultsRunner.class)
public class BitmapTest {
    @Test
    public void shouldCreateScaledBitmap() throws Exception {
        Bitmap originalBitmap = Robolectric.newInstanceOf(Bitmap.class);
        shadowOf(originalBitmap).appendDescription("Original bitmap");
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 100, 200, false);
        assertEquals("Original bitmap scaled to 100 x 200 with filter false", shadowOf(scaledBitmap).getDescription());
        assertEquals(100, scaledBitmap.getWidth());
        assertEquals(200, scaledBitmap.getHeight());
    }

    @Test
    public void equals_shouldCompareDescriptions() throws Exception {
        assertFalse(ShadowBitmap.create("bitmap A").equals(ShadowBitmap.create("bitmap B")));

        assertTrue(ShadowBitmap.create("bitmap A").equals(ShadowBitmap.create("bitmap A")));
    }

    @Test
    public void equals_shouldCompareWidthAndHeight() throws Exception {
        Bitmap bitmapA1 = ShadowBitmap.create("bitmap A");
        shadowOf(bitmapA1).setWidth(100);
        shadowOf(bitmapA1).setHeight(100);

        Bitmap bitmapA2 = ShadowBitmap.create("bitmap A");
        shadowOf(bitmapA2).setWidth(101);
        shadowOf(bitmapA2).setHeight(101);

        assertFalse(bitmapA1.equals(bitmapA2));
    }
}
