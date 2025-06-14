package com.hzbhd.ui.view.paged;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import androidx.core.app.NotificationCompat;
import androidx.core.view.MotionEventCompat;
import com.hzbhd.canbus.ui_set.SyncAction;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.ui.view.paged.PagedView;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PagedView.kt */
@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0014\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0016\u0018\u0000 ë\u00022\u00020\u00012\u00020\u0002:\në\u0002ì\u0002í\u0002î\u0002ï\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB)\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0013\u0010Å\u0001\u001a\u00030Æ\u00012\u0007\u0010Ç\u0001\u001a\u00020\u0017H\u0002J\u0014\u0010È\u0001\u001a\u00030Æ\u00012\b\u0010É\u0001\u001a\u00030Ê\u0001H\u0002J,\u0010Ë\u0001\u001a\u00030Æ\u00012\u000e\u0010Ì\u0001\u001a\t\u0012\u0004\u0012\u00020=0Í\u00012\u0007\u0010Î\u0001\u001a\u00020\n2\u0007\u0010Ï\u0001\u001a\u00020\nH\u0016J\u0013\u0010Ð\u0001\u001a\u00030Æ\u00012\t\u0010Ñ\u0001\u001a\u0004\u0018\u00010=J\n\u0010Ò\u0001\u001a\u00030Æ\u0001H\u0002J\n\u0010Ó\u0001\u001a\u00030Æ\u0001H\u0004J\u0013\u0010Ô\u0001\u001a\u00020\u00172\b\u0010Õ\u0001\u001a\u00030Ö\u0001H\u0014J\n\u0010×\u0001\u001a\u00030Æ\u0001H\u0016J\t\u0010Ø\u0001\u001a\u00020\u0017H\u0004J\u001f\u0010Ù\u0001\u001a\u00030Æ\u00012\b\u0010É\u0001\u001a\u00030Ê\u00012\t\b\u0002\u0010Ú\u0001\u001a\u000204H\u0004J\b\u0010Û\u0001\u001a\u00030Æ\u0001J\b\u0010Ü\u0001\u001a\u00030Æ\u0001J\u0014\u0010Ý\u0001\u001a\u00030Æ\u00012\b\u0010Þ\u0001\u001a\u00030ß\u0001H\u0014J\u0013\u0010à\u0001\u001a\u00020\u00172\b\u0010É\u0001\u001a\u00030Ê\u0001H\u0016J\u001b\u0010á\u0001\u001a\u00020\u00172\u0007\u0010â\u0001\u001a\u00020=2\u0007\u0010Î\u0001\u001a\u00020\nH\u0016J\u0012\u0010ã\u0001\u001a\u0002042\u0007\u0010ä\u0001\u001a\u000204H\u0002J\b\u0010å\u0001\u001a\u00030Æ\u0001J\b\u0010æ\u0001\u001a\u00030Æ\u0001J\b\u0010ç\u0001\u001a\u00030Æ\u0001J\u0013\u0010è\u0001\u001a\u00030Æ\u00012\u0007\u0010â\u0001\u001a\u00020=H\u0016J\n\u0010é\u0001\u001a\u00030Æ\u0001H\u0002J\n\u0010ê\u0001\u001a\u00030ë\u0001H\u0014J\u0012\u0010ì\u0001\u001a\u00030ë\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0014\u0010ì\u0001\u001a\u00030Ö\u00012\b\u0010Õ\u0001\u001a\u00030Ö\u0001H\u0014J\u0012\u0010í\u0001\u001a\u00020\n2\u0007\u0010î\u0001\u001a\u00020\nH\u0004J\u0013\u0010ï\u0001\u001a\u00030Æ\u00012\u0007\u0010ð\u0001\u001a\u00020{H\u0004J\u0010\u0010ñ\u0001\u001a\u00020\n2\u0007\u0010î\u0001\u001a\u00020\nJ\u0012\u0010ò\u0001\u001a\u0004\u0018\u00010=2\u0007\u0010î\u0001\u001a\u00020\nJ\u0012\u0010ó\u0001\u001a\u00020\n2\t\u0010ô\u0001\u001a\u0004\u0018\u00010=J\u0010\u0010õ\u0001\u001a\u00020\n2\u0007\u0010î\u0001\u001a\u00020\nJ$\u0010ö\u0001\u001a\u0002042\u0007\u0010÷\u0001\u001a\u00020\n2\u0007\u0010ô\u0001\u001a\u00020=2\u0007\u0010Ñ\u0001\u001a\u00020\nH\u0004J\u0013\u0010ø\u0001\u001a\u00030Æ\u00012\u0007\u0010ð\u0001\u001a\u00020{H\u0004J\u001b\u0010ù\u0001\u001a\u00020\u00172\u0007\u0010ú\u0001\u001a\u0002042\u0007\u0010û\u0001\u001a\u000204H\u0004J\u001b\u0010ü\u0001\u001a\u00020\u00172\u0007\u0010ú\u0001\u001a\u0002042\u0007\u0010û\u0001\u001a\u000204H\u0004J\u0012\u0010ý\u0001\u001a\u00020\n2\u0007\u0010î\u0001\u001a\u00020\nH\u0004J\n\u0010þ\u0001\u001a\u00030Æ\u0001H\u0004J\u0010\u0010ÿ\u0001\u001a\u00020\u00172\u0007\u0010\u0080\u0002\u001a\u00020\u0017J\u001b\u0010\u0081\u0002\u001a\u00020\u00172\u0007\u0010ú\u0001\u001a\u00020\n2\u0007\u0010û\u0001\u001a\u00020\nH\u0002J%\u0010\u0082\u0002\u001a\u00030\u0083\u00022\u0007\u0010ô\u0001\u001a\u00020=2\u0007\u0010ú\u0001\u001a\u0002042\u0007\u0010û\u0001\u001a\u000204H\u0002J%\u0010\u0084\u0002\u001a\u00030\u0083\u00022\u0007\u0010ô\u0001\u001a\u00020=2\u0007\u0010ú\u0001\u001a\u0002042\u0007\u0010û\u0001\u001a\u000204H\u0002J\n\u0010\u0085\u0002\u001a\u00030Æ\u0001H\u0004J\n\u0010\u0086\u0002\u001a\u00030Æ\u0001H\u0014J\u001c\u0010\u0087\u0002\u001a\u00030Æ\u00012\u0007\u0010\u0088\u0002\u001a\u00020=2\u0007\u0010\u0089\u0002\u001a\u00020=H\u0016J\u001c\u0010\u008a\u0002\u001a\u00030Æ\u00012\u0007\u0010\u0088\u0002\u001a\u00020=2\u0007\u0010\u0089\u0002\u001a\u00020=H\u0016J\b\u0010\u008b\u0002\u001a\u00030Æ\u0001J\u0013\u0010\u008c\u0002\u001a\u00020\u00172\b\u0010\u008d\u0002\u001a\u00030Ê\u0001H\u0016J\u0013\u0010\u008e\u0002\u001a\u00020\u00172\b\u0010\u008d\u0002\u001a\u00030Ê\u0001H\u0016J\u0014\u0010\u008f\u0002\u001a\u00030Æ\u00012\b\u0010\u008d\u0002\u001a\u00030\u0090\u0002H\u0016J\u0014\u0010\u0091\u0002\u001a\u00030Æ\u00012\b\u0010\u0092\u0002\u001a\u00030\u0093\u0002H\u0017J\u0013\u0010\u0094\u0002\u001a\u00020\u00172\b\u0010É\u0001\u001a\u00030Ê\u0001H\u0016J7\u0010\u0095\u0002\u001a\u00030Æ\u00012\u0007\u0010\u0096\u0002\u001a\u00020\u00172\u0007\u0010\u0097\u0002\u001a\u00020\n2\u0007\u0010\u0098\u0002\u001a\u00020\n2\u0007\u0010\u0099\u0002\u001a\u00020\n2\u0007\u0010\u009a\u0002\u001a\u00020\nH\u0014J\u001c\u0010\u009b\u0002\u001a\u00030Æ\u00012\u0007\u0010\u009c\u0002\u001a\u00020\n2\u0007\u0010\u009d\u0002\u001a\u00020\nH\u0014J\n\u0010\u009e\u0002\u001a\u00030Æ\u0001H\u0014J\n\u0010\u009f\u0002\u001a\u00030Æ\u0001H\u0014J\b\u0010 \u0002\u001a\u00030Æ\u0001J\u001d\u0010¡\u0002\u001a\u00020\u00172\u0007\u0010Î\u0001\u001a\u00020\n2\t\u0010¢\u0002\u001a\u0004\u0018\u00010VH\u0014J\n\u0010£\u0002\u001a\u00030Æ\u0001H\u0004J\n\u0010¤\u0002\u001a\u00030Æ\u0001H\u0004J\u0014\u0010¥\u0002\u001a\u00030Æ\u00012\b\u0010É\u0001\u001a\u00030Ê\u0001H\u0002J\b\u0010¦\u0002\u001a\u00030Æ\u0001J\u0013\u0010§\u0002\u001a\u00020\u00172\b\u0010É\u0001\u001a\u00030Ê\u0001H\u0016J\u0016\u0010¨\u0002\u001a\u00030Æ\u00012\n\u0010É\u0001\u001a\u0005\u0018\u00010Ê\u0001H\u0004J\u001c\u0010©\u0002\u001a\u00030Æ\u00012\u0007\u0010ª\u0002\u001a\u00020=2\u0007\u0010«\u0002\u001a\u00020\nH\u0014J\n\u0010¬\u0002\u001a\u00030Æ\u0001H\u0004J\n\u0010\u00ad\u0002\u001a\u00030Æ\u0001H\u0004J\u001c\u0010®\u0002\u001a\u00020\u00172\u0007\u0010¯\u0002\u001a\u00020\n2\b\u0010°\u0002\u001a\u00030±\u0002H\u0016J\t\u0010²\u0002\u001a\u00020\u0017H\u0016J\n\u0010³\u0002\u001a\u00030Æ\u0001H\u0002J\n\u0010´\u0002\u001a\u00030Æ\u0001H\u0016J\u0013\u0010µ\u0002\u001a\u00030Æ\u00012\u0007\u0010î\u0001\u001a\u00020\nH\u0002J\u0013\u0010¶\u0002\u001a\u00030Æ\u00012\u0007\u0010ô\u0001\u001a\u00020=H\u0016J\u0013\u0010·\u0002\u001a\u00030Æ\u00012\u0007\u0010î\u0001\u001a\u00020\nH\u0016J\u0013\u0010¸\u0002\u001a\u00030Æ\u00012\u0007\u0010ô\u0001\u001a\u00020=H\u0016J\u001c\u0010¹\u0002\u001a\u00030Æ\u00012\u0007\u0010\u0089\u0002\u001a\u00020=2\u0007\u0010â\u0001\u001a\u00020=H\u0016J$\u0010º\u0002\u001a\u00020\u00172\u0007\u0010\u0089\u0002\u001a\u00020=2\u0007\u0010»\u0002\u001a\u00020V2\u0007\u0010¼\u0002\u001a\u00020\u0017H\u0016J\u0013\u0010½\u0002\u001a\u00030Æ\u00012\u0007\u0010¾\u0002\u001a\u00020\u0017H\u0016J\n\u0010¿\u0002\u001a\u00030Æ\u0001H\u0002J\u0013\u0010À\u0002\u001a\u00030Æ\u00012\u0007\u0010÷\u0001\u001a\u00020\nH\u0004J\u001c\u0010Á\u0002\u001a\u00030Æ\u00012\u0007\u0010ú\u0001\u001a\u00020\n2\u0007\u0010û\u0001\u001a\u00020\nH\u0016J\b\u0010Â\u0002\u001a\u00030Æ\u0001J\u0007\u0010Ã\u0002\u001a\u00020\u0017J\u001c\u0010Ä\u0002\u001a\u00030Æ\u00012\u0007\u0010ú\u0001\u001a\u00020\n2\u0007\u0010û\u0001\u001a\u00020\nH\u0016J\u0013\u0010Å\u0002\u001a\u00030Æ\u00012\u0007\u0010Æ\u0002\u001a\u00020\nH\u0016J\n\u0010Ç\u0002\u001a\u00030Æ\u0001H\u0002J\u0015\u0010È\u0002\u001a\u00030Æ\u00012\t\u0010É\u0002\u001a\u0004\u0018\u000102H\u0004J\u0013\u0010Ê\u0002\u001a\u00030Æ\u00012\u0007\u0010Ë\u0002\u001a\u00020\u0017H\u0002J\u0013\u0010Ì\u0002\u001a\u00030Æ\u00012\u0007\u0010Í\u0002\u001a\u00020\u0017H\u0004J\u0011\u0010Î\u0002\u001a\u00030Æ\u00012\u0007\u0010ä\u0001\u001a\u000204J\u0015\u0010Ï\u0002\u001a\u00030Æ\u00012\t\u0010Ð\u0002\u001a\u0004\u0018\u00010gH\u0016J\u0011\u0010Ñ\u0002\u001a\u00030Æ\u00012\u0007\u0010Ò\u0002\u001a\u00020\nJ\u0014\u0010Ó\u0002\u001a\u00030Æ\u00012\n\u0010Ô\u0002\u001a\u0005\u0018\u00010\u0080\u0001J\u0013\u0010Õ\u0002\u001a\u00030Æ\u00012\u0007\u0010Ö\u0002\u001a\u000204H\u0016J\u0012\u0010×\u0002\u001a\u00020\u00172\u0007\u0010\u0089\u0002\u001a\u00020=H\u0004J\n\u0010Ø\u0002\u001a\u00030Æ\u0001H\u0004J\u0011\u0010Ù\u0002\u001a\u00030Æ\u00012\u0007\u0010Ú\u0002\u001a\u00020\nJ(\u0010Ù\u0002\u001a\u00030Æ\u00012\u0007\u0010Ú\u0002\u001a\u00020\n2\u0007\u0010Û\u0002\u001a\u00020\n2\n\u0010É\u0002\u001a\u0005\u0018\u00010Ü\u0002H\u0004J5\u0010Ù\u0002\u001a\u00030Æ\u00012\u0007\u0010Ú\u0002\u001a\u00020\n2\u0007\u0010Û\u0002\u001a\u00020\n2\t\b\u0002\u0010¼\u0002\u001a\u00020\u00172\f\b\u0002\u0010É\u0002\u001a\u0005\u0018\u00010Ü\u0002H\u0004J>\u0010Ù\u0002\u001a\u00030Æ\u00012\u0007\u0010Ú\u0002\u001a\u00020\n2\u0007\u0010Ý\u0002\u001a\u00020\n2\u0007\u0010Û\u0002\u001a\u00020\n2\t\b\u0002\u0010¼\u0002\u001a\u00020\u00172\f\b\u0002\u0010É\u0002\u001a\u0005\u0018\u00010Ü\u0002H\u0004J\u0011\u0010Þ\u0002\u001a\u00030Æ\u00012\u0007\u0010Ú\u0002\u001a\u00020\nJ\u001c\u0010ß\u0002\u001a\u00030Æ\u00012\u0007\u0010Ú\u0002\u001a\u00020\n2\u0007\u0010à\u0002\u001a\u00020\nH\u0004J\u0012\u0010á\u0002\u001a\u00020\u00172\t\u0010ô\u0001\u001a\u0004\u0018\u00010=J\n\u0010â\u0002\u001a\u00030Æ\u0001H\u0004J\n\u0010ã\u0002\u001a\u00030Æ\u0001H\u0002J\b\u0010ä\u0002\u001a\u00030Æ\u0001J\b\u0010å\u0002\u001a\u00030Æ\u0001J\n\u0010æ\u0002\u001a\u00030Æ\u0001H\u0002J\u001c\u0010æ\u0002\u001a\u00030Æ\u00012\u0007\u0010ç\u0002\u001a\u00020\n2\u0007\u0010è\u0002\u001a\u00020\nH\u0014J\u0012\u0010é\u0002\u001a\u00020\n2\u0007\u0010ê\u0002\u001a\u00020\nH\u0002R\u000e\u0010\u000e\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\n8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0011\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0011\"\u0004\b\u001d\u0010\u0015R\u001a\u0010\u001e\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR\u000e\u0010!\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0011\"\u0004\b$\u0010\u0015R\u001a\u0010%\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0011\"\u0004\b'\u0010\u0015R\u001a\u0010(\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0018\"\u0004\b*\u0010\u001aR\u001a\u0010+\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0011\"\u0004\b-\u0010\u0015R\u001a\u0010.\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0011\"\u0004\b0\u0010\u0015R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00103\u001a\u000204X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u000e\u00109\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010<\u001a\u0004\u0018\u00010=X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u000e\u0010B\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010C\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u0018\"\u0004\bE\u0010\u001aR\u001a\u0010F\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u0018\"\u0004\bH\u0010\u001aR\u001a\u0010I\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u0011\"\u0004\bK\u0010\u0015R\u001a\u0010L\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u0018\"\u0004\bN\u0010\u001aR\u001a\u0010O\u001a\u00020\u0017X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\u0018\"\u0004\bQ\u0010\u001aR\u000e\u0010R\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010U\u001a\u00020VX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\bW\u0010XR\u000e\u0010Y\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010Z\u001a\u00020\u0017X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\u0018R\u001a\u0010\\\u001a\u000204X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u00106\"\u0004\b^\u00108R\u001a\u0010_\u001a\u000204X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u00106\"\u0004\ba\u00108R\u001a\u0010b\u001a\u000204X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u00106\"\u0004\bd\u00108R\u000e\u0010e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010f\u001a\u0004\u0018\u00010gX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010i\"\u0004\bj\u0010kR\u001a\u0010l\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010\u0011\"\u0004\bn\u0010\u0015R\u000e\u0010o\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010p\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010\u0011\"\u0004\br\u0010\u0015R\u000e\u0010s\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010t\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bu\u0010\u0011\"\u0004\bv\u0010\u0015R\u001a\u0010w\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010\u0011\"\u0004\by\u0010\u0015R\u0010\u0010z\u001a\u0004\u0018\u00010{X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010|\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b}\u0010\u0011\"\u0004\b~\u0010\u0015R\u0011\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0081\u0001\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0082\u0001\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0083\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0086\u0001\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0087\u0001\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u0001X\u0084\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001\"\u0006\b\u008c\u0001\u0010\u008d\u0001R\u001d\u0010\u008e\u0001\u001a\u00020\nX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008f\u0001\u0010\u0011\"\u0005\b\u0090\u0001\u0010\u0015R\u0012\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u0085\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0092\u0001\u001a\u000204X\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0093\u0001\u00106\"\u0005\b\u0094\u0001\u00108R\u001f\u0010\u0095\u0001\u001a\u00020{X\u0084\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001\"\u0006\b\u0098\u0001\u0010\u0099\u0001R\u001d\u0010\u009a\u0001\u001a\u000204X\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009b\u0001\u00106\"\u0005\b\u009c\u0001\u00108R\u001d\u0010\u009d\u0001\u001a\u00020\nX\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009e\u0001\u0010\u0011\"\u0005\b\u009f\u0001\u0010\u0015R\u001d\u0010 \u0001\u001a\u00020\nX\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¡\u0001\u0010\u0011\"\u0005\b¢\u0001\u0010\u0015R\u001d\u0010£\u0001\u001a\u000204X\u0084\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¤\u0001\u00106\"\u0005\b¥\u0001\u00108R\u000f\u0010¦\u0001\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010§\u0001\u001a\u0005\u0018\u00010¨\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010©\u0001\u001a\u00020VX\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010ª\u0001\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010«\u0001\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b¬\u0001\u0010\u0011R\u0013\u0010\u00ad\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\b®\u0001\u0010\u0011R!\u0010°\u0001\u001a\u00020\n2\u0007\u0010¯\u0001\u001a\u00020\n@BX\u0086\u000e¢\u0006\t\n\u0000\u001a\u0005\b±\u0001\u0010\u0011R\u0013\u0010²\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\b³\u0001\u0010\u0011R\u001a\u0010´\u0001\u001a\u0005\u0018\u00010µ\u00018DX\u0084\u0004¢\u0006\b\u001a\u0006\b¶\u0001\u0010·\u0001R\u0013\u0010¸\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\b¹\u0001\u0010\u0011R\u001d\u0010º\u0001\u001a\u00020\nX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b»\u0001\u0010\u0011\"\u0005\b¼\u0001\u0010\u0015R\u0013\u0010½\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\b¾\u0001\u0010\u0011R\u0013\u0010¿\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\bÀ\u0001\u0010\u0011R\u0013\u0010Á\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\bÂ\u0001\u0010\u0011R\u0013\u0010Ã\u0001\u001a\u00020\n8F¢\u0006\u0007\u001a\u0005\bÄ\u0001\u0010\u0011¨\u0006ð\u0002"}, d2 = {"Lcom/hzbhd/ui/view/paged/PagedView;", "Landroid/view/ViewGroup;", "Landroid/view/ViewGroup$OnHierarchyChangeListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "NUM_ANIMATIONS_RUNNING_BEFORE_ZOOM_OUT", "childGap", "getChildGap", "()I", "currentPage", "getCurrentPage", "setCurrentPage", "(I)V", "isPageMoving", "", "()Z", "setPageMoving", "(Z)V", "mActivePointerId", "getMActivePointerId", "setMActivePointerId", "mAllowOverScroll", "getMAllowOverScroll", "setMAllowOverScroll", "mCancelTap", "mCellCountX", "getMCellCountX", "setMCellCountX", "mCellCountY", "getMCellCountY", "setMCellCountY", "mCenterPagesVertically", "getMCenterPagesVertically", "setMCenterPagesVertically", "mChildCountOnLastLayout", "getMChildCountOnLastLayout", "setMChildCountOnLastLayout", "mCurrentPage", "getMCurrentPage", "setMCurrentPage", "mDefaultInterpolator", "Landroid/view/animation/Interpolator;", "mDensity", "", "getMDensity", "()F", "setMDensity", "(F)V", "mDownMotionX", "mDownMotionY", "mDownScrollX", "mDragView", "Landroid/view/View;", "getMDragView", "()Landroid/view/View;", "setMDragView", "(Landroid/view/View;)V", "mDragViewBaselineLeft", "mFadeInAdjacentScreens", "getMFadeInAdjacentScreens", "setMFadeInAdjacentScreens", "mFirstLayout", "getMFirstLayout", "setMFirstLayout", "mFlingThresholdVelocity", "getMFlingThresholdVelocity", "setMFlingThresholdVelocity", "mForceDrawAllChildrenNextFrame", "getMForceDrawAllChildrenNextFrame", "setMForceDrawAllChildrenNextFrame", "mForceScreenScrolled", "getMForceScreenScrolled", "setMForceScreenScrolled", "mFreeScroll", "mFreeScrollMaxScrollX", "mFreeScrollMinScrollX", "mInsets", "Landroid/graphics/Rect;", "getMInsets", "()Landroid/graphics/Rect;", "mIsReordering", "mIsRtl", "getMIsRtl", "mLastMotionX", "getMLastMotionX", "setMLastMotionX", "mLastMotionXRemainder", "getMLastMotionXRemainder", "setMLastMotionXRemainder", "mLastMotionY", "getMLastMotionY", "setMLastMotionY", "mLastScreenCenter", "mLongClickListener", "Landroid/view/View$OnLongClickListener;", "getMLongClickListener", "()Landroid/view/View$OnLongClickListener;", "setMLongClickListener", "(Landroid/view/View$OnLongClickListener;)V", "mMaxScrollX", "getMMaxScrollX", "setMMaxScrollX", "mMaximumVelocity", "mMinFlingVelocity", "getMMinFlingVelocity", "setMMinFlingVelocity", "mMinScale", "mMinSnapVelocity", "getMMinSnapVelocity", "setMMinSnapVelocity", "mNextPage", "getMNextPage", "setMNextPage", "mPageScrolls", "", "mPageSpacing", "getMPageSpacing", "setMPageSpacing", "mPageSwitchListener", "Lcom/hzbhd/ui/view/paged/PagedView$PageSwitchListener;", "mParentDownMotionX", "mParentDownMotionY", "mPostReorderingPreZoomInRemainingAnimationCount", "mPostReorderingPreZoomInRunnable", "Ljava/lang/Runnable;", "mReorderingStarted", "mScrollAble", "mScroller", "Lcom/hzbhd/ui/view/paged/LauncherScroller;", "getMScroller", "()Lcom/hzbhd/ui/view/paged/LauncherScroller;", "setMScroller", "(Lcom/hzbhd/ui/view/paged/LauncherScroller;)V", "mSidePageHoverIndex", "getMSidePageHoverIndex", "setMSidePageHoverIndex", "mSidePageHoverRunnable", "mSmoothingTime", "getMSmoothingTime", "setMSmoothingTime", "mTempVisiblePagesRange", "getMTempVisiblePagesRange", "()[I", "setMTempVisiblePagesRange", "([I)V", "mTotalMotionX", "getMTotalMotionX", "setMTotalMotionX", "mTouchSlop", "getMTouchSlop", "setMTouchSlop", "mTouchState", "getMTouchState", "setMTouchState", "mTouchX", "getMTouchX", "setMTouchX", "mUseMinScale", "mVelocityTracker", "Landroid/view/VelocityTracker;", "mViewport", "mWasInOverscroll", "nearestHoverOverPageIndex", "getNearestHoverOverPageIndex", "nextPage", "getNextPage", "<set-?>", "normalChildHeight", "getNormalChildHeight", "pageCount", "getPageCount", "pageIndicatorClickListener", "Landroid/view/View$OnClickListener;", "getPageIndicatorClickListener", "()Landroid/view/View$OnClickListener;", "pageNearestToCenterOfScreen", "getPageNearestToCenterOfScreen", "restorePage", "getRestorePage", "setRestorePage", "viewportHeight", "getViewportHeight", "viewportOffsetX", "getViewportOffsetX", "viewportOffsetY", "getViewportOffsetY", "viewportWidth", "getViewportWidth", "abortScrollerAnimation", "", "resetNextPage", "acquireVelocityTrackerAndAddMovement", "ev", "Landroid/view/MotionEvent;", "addFocusables", "views", "Ljava/util/ArrayList;", "direction", "focusableMode", "addFullScreenPage", "page", "animateDragViewToOriginalPosition", "cancelCurrentPageLongPress", "checkLayoutParams", "p", "Landroid/view/ViewGroup$LayoutParams;", "computeScroll", "computeScrollHelper", "determineScrollingStart", "touchSlopScale", "didAbleScroll", "disableFreeScroll", "dispatchDraw", "canvas", "Landroid/graphics/Canvas;", "dispatchTouchEvent", "dispatchUnhandledMove", "focused", "distanceInfluenceForSnapDuration", "f", "enableFreeScroll", "enableScroll", "endReordering", "focusableViewAvailable", "forceFinishScroller", "generateDefaultLayoutParams", "Lcom/hzbhd/ui/view/paged/PagedView$LayoutParams;", "generateLayoutParams", "getChildOffset", "index", "getFreeScrollPageRange", "range", "getLayoutTransitionOffsetForPage", "getPageAt", "getPageForView", "v", "getScrollForPage", "getScrollProgress", "screenCenter", "getVisiblePages", "hitsNextPage", "x", "y", "hitsPreviousPage", "indexToPage", "init", "isReordering", "testTouchState", "isTouchPointInViewportWithBuffer", "mapPointFromParentToView", "", "mapPointFromViewToParent", "notifyPageSwitchListener", "onAttachedToWindow", "onChildViewAdded", "parent", "child", "onChildViewRemoved", "onEndReordering", "onGenericMotionEvent", NotificationCompat.CATEGORY_EVENT, "onHoverEvent", "onInitializeAccessibilityEvent", "Landroid/view/accessibility/AccessibilityEvent;", "onInitializeAccessibilityNodeInfo", SyncAction.KEYBOARD_INFO, "Landroid/view/accessibility/AccessibilityNodeInfo;", "onInterceptTouchEvent", "onLayout", "changed", "left", "top", "right", "bottom", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onPageBeginMoving", "onPageEndMoving", "onPostReorderingAnimationCompleted", "onRequestFocusInDescendants", "previouslyFocusedRect", "onScrollInteractionBegin", "onScrollInteractionEnd", "onSecondaryPointerUp", "onStartReordering", "onTouchEvent", "onUnhandledTap", "onVisibilityChanged", "changedView", "visibility", "pageBeginMoving", "pageEndMoving", "performAccessibilityAction", "action", "arguments", "Landroid/os/Bundle;", "performLongClick", "releaseVelocityTracker", "removeAllViewsInLayout", "removeMarkerForView", "removeView", "removeViewAt", "removeViewInLayout", "requestChildFocus", "requestChildRectangleOnScreen", "rectangle", "immediate", "requestDisallowInterceptTouchEvent", "disallowIntercept", "resetTouchState", "screenScrolled", "scrollBy", "scrollLeft", "scrollRight", "scrollTo", "sendAccessibilityEvent", "eventType", "sendScrollAccessibilityEvent", "setDefaultInterpolator", "interpolator", "setEnableFreeScroll", "freeScroll", "setEnableOverscroll", "enable", "setMinScale", "setOnLongClickListener", "l", "setPageSpacing", "pageSpacing", "setPageSwitchListener", "pageSwitchListener", "setScaleX", "scaleX", "shouldDrawChild", "snapToDestination", "snapToPage", "whichPage", "duration", "Landroid/animation/TimeInterpolator;", "delta", "snapToPageImmediately", "snapToPageWithVelocity", "velocity", "startReordering", "updateCurrentPageScroll", "updateDragViewTranslationDuringDrag", "updateFreescrollBounds", "updateMaxScrollX", "updatePageIndicator", "curr", "max", "validateNewPage", "newPage", "Companion", "LayoutParams", "PageSwitchListener", "SavedState", "ScrollInterpolator", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public class PagedView extends ViewGroup implements ViewGroup.OnHierarchyChangeListener {
    protected static final float ALPHA_QUANTIZE_LEVEL = 1.0E-4f;
    private static final int ANIM_TAG_KEY = 100;
    public static final int AUTOMATIC_PAGE_SPACING = -1;
    private static final boolean DEBUG = false;
    private static final int FLING_THRESHOLD_VELOCITY = 500;
    protected static final int INVALID_PAGE = -1;
    protected static final int INVALID_POINTER = -1;
    public static final int INVALID_RESTORE_PAGE = -1001;
    private static final float MAX_SCROLL_PROGRESS = 1.0f;
    private static final int MIN_FLING_VELOCITY = 250;
    private static final int MIN_LENGTH_FOR_FLING = 25;
    private static final int MIN_SNAP_VELOCITY = 1500;
    protected static final float NANOTIME_DIV = 1.0E9f;
    protected static final int PAGE_SNAP_ANIMATION_DURATION = 300;
    private static final int REORDERING_DROP_REPOSITION_DURATION = 200;
    private static final int REORDERING_SIDE_PAGE_HOVER_TIMEOUT = 80;
    private static final float RETURN_TO_ORIGINAL_PAGE_THRESHOLD = 0.33f;
    private static final float SIGNIFICANT_MOVE_THRESHOLD = 0.4f;
    protected static final int SLOW_PAGE_SNAP_ANIMATION_DURATION = 300;
    private static final String TAG = "PagedView";
    protected static final int TOUCH_STATE_NEXT_PAGE = 3;
    protected static final int TOUCH_STATE_PREV_PAGE = 2;
    protected static final int TOUCH_STATE_REORDERING = 4;
    protected static final int TOUCH_STATE_REST = 0;
    protected static final int TOUCH_STATE_SCROLLING = 1;
    private final int NUM_ANIMATIONS_RUNNING_BEFORE_ZOOM_OUT;
    private boolean isPageMoving;
    private int mActivePointerId;
    private boolean mAllowOverScroll;
    private boolean mCancelTap;
    private int mCellCountX;
    private int mCellCountY;
    private boolean mCenterPagesVertically;
    private int mChildCountOnLastLayout;
    private int mCurrentPage;
    private Interpolator mDefaultInterpolator;
    private float mDensity;
    private float mDownMotionX;
    private float mDownMotionY;
    private float mDownScrollX;
    private View mDragView;
    private float mDragViewBaselineLeft;
    private boolean mFadeInAdjacentScreens;
    private boolean mFirstLayout;
    private int mFlingThresholdVelocity;
    private boolean mForceDrawAllChildrenNextFrame;
    private boolean mForceScreenScrolled;
    private boolean mFreeScroll;
    private int mFreeScrollMaxScrollX;
    private int mFreeScrollMinScrollX;
    private final Rect mInsets;
    private boolean mIsReordering;
    private final boolean mIsRtl;
    private float mLastMotionX;
    private float mLastMotionXRemainder;
    private float mLastMotionY;
    private int mLastScreenCenter;
    private View.OnLongClickListener mLongClickListener;
    private int mMaxScrollX;
    private int mMaximumVelocity;
    private int mMinFlingVelocity;
    private float mMinScale;
    private int mMinSnapVelocity;
    private int mNextPage;
    private int[] mPageScrolls;
    private int mPageSpacing;
    private PageSwitchListener mPageSwitchListener;
    private float mParentDownMotionX;
    private float mParentDownMotionY;
    private int mPostReorderingPreZoomInRemainingAnimationCount;
    private Runnable mPostReorderingPreZoomInRunnable;
    private boolean mReorderingStarted;
    private boolean mScrollAble;
    private LauncherScroller mScroller;
    private int mSidePageHoverIndex;
    private Runnable mSidePageHoverRunnable;
    private float mSmoothingTime;
    private int[] mTempVisiblePagesRange;
    private float mTotalMotionX;
    private int mTouchSlop;
    private int mTouchState;
    private float mTouchX;
    private boolean mUseMinScale;
    private VelocityTracker mVelocityTracker;
    private final Rect mViewport;
    private boolean mWasInOverscroll;
    private int normalChildHeight;
    private int restorePage;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static int REORDERING_REORDER_REPOSITION_DURATION = 300;
    private static final Matrix sTmpInvMatrix = new Matrix();
    private static final float[] sTmpPoint = new float[2];
    private static final int[] sTmpIntPoint = new int[2];
    private static final Rect sTmpRect = new Rect();

    /* compiled from: PagedView.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/hzbhd/ui/view/paged/PagedView$PageSwitchListener;", "", "onPageSwitch", "", "newPage", "Landroid/view/View;", "newPageIndex", "", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public interface PageSwitchListener {
        void onPageSwitch(View newPage, int newPageIndex);
    }

    /* compiled from: PagedView.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/hzbhd/ui/view/paged/PagedView$ScrollInterpolator;", "Landroid/view/animation/Interpolator;", "()V", "getInterpolation", "", "t", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    private static final class ScrollInterpolator implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float t) {
            float f = t - PagedView.MAX_SCROLL_PROGRESS;
            return (f * f * f * f * f) + 1;
        }
    }

    protected final int getChildGap() {
        return 0;
    }

    protected final View.OnClickListener getPageIndicatorClickListener() {
        return null;
    }

    protected final int indexToPage(int index) {
        return index;
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return true;
    }

    protected void onPageBeginMoving() {
    }

    protected final void onScrollInteractionBegin() {
    }

    protected final void onScrollInteractionEnd() {
    }

    protected final void onUnhandledTap(MotionEvent ev) {
    }

    protected final void screenScrolled(int screenCenter) {
    }

    protected void updatePageIndicator(int curr, int max) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagedView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mFreeScrollMinScrollX = -1;
        this.mFreeScrollMaxScrollX = -1;
        this.mFirstLayout = true;
        this.restorePage = INVALID_RESTORE_PAGE;
        this.mNextPage = -1;
        this.mLastScreenCenter = -1;
        this.mAllowOverScroll = true;
        this.mTempVisiblePagesRange = new int[2];
        this.mActivePointerId = -1;
        this.mViewport = new Rect();
        this.mMinScale = MAX_SCROLL_PROGRESS;
        this.mSidePageHoverIndex = -1;
        this.NUM_ANIMATIONS_RUNNING_BEFORE_ZOOM_OUT = 2;
        this.mInsets = new Rect();
        setHapticFeedbackEnabled(false);
        this.mIsRtl = false;
        init();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.mFreeScrollMinScrollX = -1;
        this.mFreeScrollMaxScrollX = -1;
        this.mFirstLayout = true;
        this.restorePage = INVALID_RESTORE_PAGE;
        this.mNextPage = -1;
        this.mLastScreenCenter = -1;
        this.mAllowOverScroll = true;
        this.mTempVisiblePagesRange = new int[2];
        this.mActivePointerId = -1;
        this.mViewport = new Rect();
        this.mMinScale = MAX_SCROLL_PROGRESS;
        this.mSidePageHoverIndex = -1;
        this.NUM_ANIMATIONS_RUNNING_BEFORE_ZOOM_OUT = 2;
        this.mInsets = new Rect();
        setHapticFeedbackEnabled(false);
        this.mIsRtl = false;
        init();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagedView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mFreeScrollMinScrollX = -1;
        this.mFreeScrollMaxScrollX = -1;
        this.mFirstLayout = true;
        this.restorePage = INVALID_RESTORE_PAGE;
        this.mNextPage = -1;
        this.mLastScreenCenter = -1;
        this.mAllowOverScroll = true;
        this.mTempVisiblePagesRange = new int[2];
        this.mActivePointerId = -1;
        this.mViewport = new Rect();
        this.mMinScale = MAX_SCROLL_PROGRESS;
        this.mSidePageHoverIndex = -1;
        this.NUM_ANIMATIONS_RUNNING_BEFORE_ZOOM_OUT = 2;
        this.mInsets = new Rect();
        setHapticFeedbackEnabled(false);
        this.mIsRtl = false;
        init();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagedView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mFreeScrollMinScrollX = -1;
        this.mFreeScrollMaxScrollX = -1;
        this.mFirstLayout = true;
        this.restorePage = INVALID_RESTORE_PAGE;
        this.mNextPage = -1;
        this.mLastScreenCenter = -1;
        this.mAllowOverScroll = true;
        this.mTempVisiblePagesRange = new int[2];
        this.mActivePointerId = -1;
        this.mViewport = new Rect();
        this.mMinScale = MAX_SCROLL_PROGRESS;
        this.mSidePageHoverIndex = -1;
        this.NUM_ANIMATIONS_RUNNING_BEFORE_ZOOM_OUT = 2;
        this.mInsets = new Rect();
        setHapticFeedbackEnabled(false);
        this.mIsRtl = false;
        init();
    }

    protected final int getMFlingThresholdVelocity() {
        return this.mFlingThresholdVelocity;
    }

    protected final void setMFlingThresholdVelocity(int i) {
        this.mFlingThresholdVelocity = i;
    }

    protected final int getMMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    protected final void setMMinFlingVelocity(int i) {
        this.mMinFlingVelocity = i;
    }

    protected final int getMMinSnapVelocity() {
        return this.mMinSnapVelocity;
    }

    protected final void setMMinSnapVelocity(int i) {
        this.mMinSnapVelocity = i;
    }

    protected final float getMDensity() {
        return this.mDensity;
    }

    protected final void setMDensity(float f) {
        this.mDensity = f;
    }

    protected final float getMSmoothingTime() {
        return this.mSmoothingTime;
    }

    protected final void setMSmoothingTime(float f) {
        this.mSmoothingTime = f;
    }

    protected final float getMTouchX() {
        return this.mTouchX;
    }

    protected final void setMTouchX(float f) {
        this.mTouchX = f;
    }

    protected final boolean getMFirstLayout() {
        return this.mFirstLayout;
    }

    protected final void setMFirstLayout(boolean z) {
        this.mFirstLayout = z;
    }

    public final int getNormalChildHeight() {
        return this.normalChildHeight;
    }

    protected final int getMCurrentPage() {
        return this.mCurrentPage;
    }

    protected final void setMCurrentPage(int i) {
        this.mCurrentPage = i;
    }

    public final int getRestorePage() {
        return this.restorePage;
    }

    public final void setRestorePage(int i) {
        this.restorePage = i;
    }

    protected final int getMChildCountOnLastLayout() {
        return this.mChildCountOnLastLayout;
    }

    protected final void setMChildCountOnLastLayout(int i) {
        this.mChildCountOnLastLayout = i;
    }

    protected final int getMNextPage() {
        return this.mNextPage;
    }

    protected final void setMNextPage(int i) {
        this.mNextPage = i;
    }

    protected final int getMMaxScrollX() {
        return this.mMaxScrollX;
    }

    protected final void setMMaxScrollX(int i) {
        this.mMaxScrollX = i;
    }

    protected final LauncherScroller getMScroller() {
        return this.mScroller;
    }

    protected final void setMScroller(LauncherScroller launcherScroller) {
        this.mScroller = launcherScroller;
    }

    public final int getMPageSpacing() {
        return this.mPageSpacing;
    }

    public final void setMPageSpacing(int i) {
        this.mPageSpacing = i;
    }

    protected final float getMLastMotionX() {
        return this.mLastMotionX;
    }

    protected final void setMLastMotionX(float f) {
        this.mLastMotionX = f;
    }

    protected final float getMLastMotionXRemainder() {
        return this.mLastMotionXRemainder;
    }

    protected final void setMLastMotionXRemainder(float f) {
        this.mLastMotionXRemainder = f;
    }

    protected final float getMLastMotionY() {
        return this.mLastMotionY;
    }

    protected final void setMLastMotionY(float f) {
        this.mLastMotionY = f;
    }

    protected final float getMTotalMotionX() {
        return this.mTotalMotionX;
    }

    protected final void setMTotalMotionX(float f) {
        this.mTotalMotionX = f;
    }

    protected final int getMTouchState() {
        return this.mTouchState;
    }

    protected final void setMTouchState(int i) {
        this.mTouchState = i;
    }

    protected final boolean getMForceScreenScrolled() {
        return this.mForceScreenScrolled;
    }

    protected final void setMForceScreenScrolled(boolean z) {
        this.mForceScreenScrolled = z;
    }

    protected final View.OnLongClickListener getMLongClickListener() {
        return this.mLongClickListener;
    }

    protected final void setMLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mLongClickListener = onLongClickListener;
    }

    protected final int getMTouchSlop() {
        return this.mTouchSlop;
    }

    protected final void setMTouchSlop(int i) {
        this.mTouchSlop = i;
    }

    protected final int getMCellCountX() {
        return this.mCellCountX;
    }

    protected final void setMCellCountX(int i) {
        this.mCellCountX = i;
    }

    protected final int getMCellCountY() {
        return this.mCellCountY;
    }

    protected final void setMCellCountY(int i) {
        this.mCellCountY = i;
    }

    protected final boolean getMCenterPagesVertically() {
        return this.mCenterPagesVertically;
    }

    protected final void setMCenterPagesVertically(boolean z) {
        this.mCenterPagesVertically = z;
    }

    protected final boolean getMAllowOverScroll() {
        return this.mAllowOverScroll;
    }

    protected final void setMAllowOverScroll(boolean z) {
        this.mAllowOverScroll = z;
    }

    protected final int[] getMTempVisiblePagesRange() {
        return this.mTempVisiblePagesRange;
    }

    protected final void setMTempVisiblePagesRange(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.mTempVisiblePagesRange = iArr;
    }

    protected final boolean getMForceDrawAllChildrenNextFrame() {
        return this.mForceDrawAllChildrenNextFrame;
    }

    protected final void setMForceDrawAllChildrenNextFrame(boolean z) {
        this.mForceDrawAllChildrenNextFrame = z;
    }

    protected final int getMActivePointerId() {
        return this.mActivePointerId;
    }

    protected final void setMActivePointerId(int i) {
        this.mActivePointerId = i;
    }

    protected final boolean getMFadeInAdjacentScreens() {
        return this.mFadeInAdjacentScreens;
    }

    protected final void setMFadeInAdjacentScreens(boolean z) {
        this.mFadeInAdjacentScreens = z;
    }

    /* renamed from: isPageMoving, reason: from getter */
    protected final boolean getIsPageMoving() {
        return this.isPageMoving;
    }

    protected final void setPageMoving(boolean z) {
        this.isPageMoving = z;
    }

    protected final View getMDragView() {
        return this.mDragView;
    }

    protected final void setMDragView(View view) {
        this.mDragView = view;
    }

    public final int getMSidePageHoverIndex() {
        return this.mSidePageHoverIndex;
    }

    public final void setMSidePageHoverIndex(int i) {
        this.mSidePageHoverIndex = i;
    }

    protected final Rect getMInsets() {
        return this.mInsets;
    }

    protected final boolean getMIsRtl() {
        return this.mIsRtl;
    }

    protected final void init() {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        this.mScroller = new LauncherScroller(context, null, false, 6, null);
        setDefaultInterpolator(new ScrollInterpolator());
        this.mCurrentPage = 0;
        this.mCenterPagesVertically = true;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        float f = getResources().getDisplayMetrics().density;
        this.mDensity = f;
        this.mFlingThresholdVelocity = (int) (500 * f);
        this.mMinFlingVelocity = (int) (250 * f);
        this.mMinSnapVelocity = (int) (MIN_SNAP_VELOCITY * f);
        setOnHierarchyChangeListener(this);
        setWillNotDraw(false);
    }

    protected final void setDefaultInterpolator(Interpolator interpolator) {
        this.mDefaultInterpolator = interpolator;
        LauncherScroller launcherScroller = this.mScroller;
        Intrinsics.checkNotNull(launcherScroller);
        launcherScroller.setInterpolator(this.mDefaultInterpolator);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
        ViewParent parent2 = ((ViewGroup) parent).getParent();
        Intrinsics.checkNotNull(parent2, "null cannot be cast to non-null type android.view.ViewGroup");
    }

    private final float[] mapPointFromViewToParent(View v, float x, float y) {
        float[] fArr = sTmpPoint;
        fArr[0] = x;
        fArr[1] = y;
        v.getMatrix().mapPoints(fArr);
        float f = fArr[0];
        v.getLeft();
        float f2 = fArr[1];
        v.getTop();
        return fArr;
    }

    private final float[] mapPointFromParentToView(View v, float x, float y) {
        float[] fArr = sTmpPoint;
        fArr[0] = x - v.getLeft();
        fArr[1] = y - v.getTop();
        Matrix matrix = v.getMatrix();
        Matrix matrix2 = sTmpInvMatrix;
        matrix.invert(matrix2);
        matrix2.mapPoints(fArr);
        return fArr;
    }

    private final void updateDragViewTranslationDuringDrag() {
        if (this.mDragView != null) {
            float scrollX = (this.mLastMotionX - this.mDownMotionX) + (getScrollX() - this.mDownScrollX);
            float f = this.mDragViewBaselineLeft;
            Intrinsics.checkNotNull(this.mDragView);
            float left = scrollX + (f - r2.getLeft());
            float f2 = this.mLastMotionY - this.mDownMotionY;
            View view = this.mDragView;
            Intrinsics.checkNotNull(view);
            view.setTranslationX(left);
            View view2 = this.mDragView;
            Intrinsics.checkNotNull(view2);
            view2.setTranslationY(f2);
            if (LogUtil.log5()) {
                LogUtil.d("PagedView.updateDragViewTranslationDuringDrag(): " + left + ", " + f2);
            }
        }
    }

    public final void setMinScale(float f) {
        this.mMinScale = f;
        this.mUseMinScale = true;
        requestLayout();
    }

    @Override // android.view.View
    public void setScaleX(float scaleX) {
        super.setScaleX(scaleX);
        if (isReordering(true)) {
            float[] fArrMapPointFromParentToView = mapPointFromParentToView(this, this.mParentDownMotionX, this.mParentDownMotionY);
            this.mLastMotionX = fArrMapPointFromParentToView[0];
            this.mLastMotionY = fArrMapPointFromParentToView[1];
            updateDragViewTranslationDuringDrag();
        }
    }

    public final int getViewportWidth() {
        return this.mViewport.width();
    }

    public final int getViewportHeight() {
        return this.mViewport.height();
    }

    public final int getViewportOffsetX() {
        return (getMeasuredWidth() - getViewportWidth()) / 2;
    }

    public final int getViewportOffsetY() {
        return (getMeasuredHeight() - getViewportHeight()) / 2;
    }

    public final void setPageSwitchListener(PageSwitchListener pageSwitchListener) {
        this.mPageSwitchListener = pageSwitchListener;
        if (pageSwitchListener != null) {
            Intrinsics.checkNotNull(pageSwitchListener);
            pageSwitchListener.onPageSwitch(getPageAt(this.mCurrentPage), this.mCurrentPage);
        }
    }

    /* renamed from: getCurrentPage, reason: from getter */
    public final int getMCurrentPage() {
        return this.mCurrentPage;
    }

    public final void setCurrentPage(int i) {
        LauncherScroller launcherScroller = this.mScroller;
        Intrinsics.checkNotNull(launcherScroller);
        if (!launcherScroller.getIsFinished()) {
            abortScrollerAnimation(true);
        }
        if (getChildCount() == 0) {
            return;
        }
        this.mForceScreenScrolled = true;
        this.mCurrentPage = validateNewPage(i);
        updateCurrentPageScroll();
        notifyPageSwitchListener();
        invalidate();
    }

    public final int getNextPage() {
        int i = this.mNextPage;
        return i != -1 ? i : this.mCurrentPage;
    }

    public final int getPageCount() {
        return getChildCount();
    }

    public final View getPageAt(int index) {
        return getChildAt(index);
    }

    protected final void updateCurrentPageScroll() {
        int i = this.mCurrentPage;
        int scrollForPage = (i < 0 || i >= getPageCount()) ? 0 : getScrollForPage(this.mCurrentPage);
        scrollTo(scrollForPage, 0);
        LauncherScroller launcherScroller = this.mScroller;
        if (launcherScroller != null) {
            launcherScroller.setFinalX(scrollForPage);
        }
        forceFinishScroller();
    }

    private final void abortScrollerAnimation(boolean resetNextPage) {
        LauncherScroller launcherScroller = this.mScroller;
        Intrinsics.checkNotNull(launcherScroller);
        launcherScroller.abortAnimation();
        if (resetNextPage) {
            this.mNextPage = -1;
        }
    }

    private final void forceFinishScroller() {
        LauncherScroller launcherScroller = this.mScroller;
        Intrinsics.checkNotNull(launcherScroller);
        launcherScroller.forceFinished(true);
        this.mNextPage = -1;
    }

    private final int validateNewPage(int newPage) {
        if (this.mFreeScroll) {
            getFreeScrollPageRange(this.mTempVisiblePagesRange);
            int[] iArr = this.mTempVisiblePagesRange;
            newPage = Math.max(iArr[0], Math.min(newPage, iArr[1]));
        }
        return Math.max(0, Math.min(newPage, getPageCount() - 1));
    }

    protected final void notifyPageSwitchListener() {
        PageSwitchListener pageSwitchListener = this.mPageSwitchListener;
        if (pageSwitchListener != null) {
            Intrinsics.checkNotNull(pageSwitchListener);
            pageSwitchListener.onPageSwitch(getPageAt(getNextPage()), getNextPage());
        }
        updatePageIndicator();
    }

    private final void updatePageIndicator() {
        updatePageIndicator(getNextPage(), getChildCount());
    }

    protected final void pageBeginMoving() {
        if (this.isPageMoving) {
            return;
        }
        this.isPageMoving = true;
        onPageBeginMoving();
    }

    protected final void pageEndMoving() {
        if (this.isPageMoving) {
            this.isPageMoving = false;
            onPageEndMoving();
        }
    }

    protected void onPageEndMoving() {
        this.mWasInOverscroll = false;
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener l) {
        this.mLongClickListener = l;
        int pageCount = getPageCount();
        for (int i = 0; i < pageCount; i++) {
            View pageAt = getPageAt(i);
            if (pageAt != null) {
                pageAt.setOnLongClickListener(l);
            }
        }
        super.setOnLongClickListener(l);
    }

    @Override // android.view.View
    public void scrollBy(int x, int y) {
        scrollTo(getScrollX() + x, getScrollY() + y);
    }

    @Override // android.view.View
    public void scrollTo(int x, int y) {
        if (this.mFreeScroll) {
            LauncherScroller launcherScroller = this.mScroller;
            Intrinsics.checkNotNull(launcherScroller);
            if (!launcherScroller.getIsFinished() && (x > this.mFreeScrollMaxScrollX || x < this.mFreeScrollMinScrollX)) {
                forceFinishScroller();
            }
            x = Math.max(Math.min(x, this.mFreeScrollMaxScrollX), this.mFreeScrollMinScrollX);
        }
        boolean z = this.mIsRtl;
        boolean z2 = !z ? x >= 0 : x <= this.mMaxScrollX;
        boolean z3 = !z ? x <= this.mMaxScrollX : x >= 0;
        if (z2) {
            super.scrollTo(z ? this.mMaxScrollX : 0, y);
            if (this.mAllowOverScroll) {
                this.mWasInOverscroll = true;
            }
        } else if (z3) {
            super.scrollTo(z ? 0 : this.mMaxScrollX, y);
            if (this.mAllowOverScroll) {
                this.mWasInOverscroll = true;
            }
        } else {
            if (this.mWasInOverscroll) {
                this.mWasInOverscroll = false;
            }
            super.scrollTo(x, y);
        }
        this.mTouchX = x;
        this.mSmoothingTime = System.nanoTime() / NANOTIME_DIV;
        if (isReordering(true)) {
            float[] fArrMapPointFromParentToView = mapPointFromParentToView(this, this.mParentDownMotionX, this.mParentDownMotionY);
            this.mLastMotionX = fArrMapPointFromParentToView[0];
            this.mLastMotionY = fArrMapPointFromParentToView[1];
            updateDragViewTranslationDuringDrag();
        }
    }

    private final void sendScrollAccessibilityEvent() {
        Object systemService = getContext().getSystemService("accessibility");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        if (!((AccessibilityManager) systemService).isEnabled() || this.mCurrentPage == getNextPage()) {
            return;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(4096);
        accessibilityEventObtain.setScrollable(true);
        accessibilityEventObtain.setScrollX(getScrollX());
        accessibilityEventObtain.setScrollY(getScrollY());
        accessibilityEventObtain.setMaxScrollX(this.mMaxScrollX);
        accessibilityEventObtain.setMaxScrollY(0);
        sendAccessibilityEventUnchecked(accessibilityEventObtain);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final boolean computeScrollHelper() {
        /*
            r4 = this;
            com.hzbhd.ui.view.paged.LauncherScroller r0 = r4.mScroller
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            boolean r0 = r0.computeScrollOffset()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L59
            com.hzbhd.ui.view.paged.LauncherScroller r0 = r4.mScroller
            if (r0 == 0) goto L1d
            int r3 = r4.getScrollX()
            int r0 = r0.getCurrX()
            if (r3 != r0) goto L1d
            r0 = r1
            goto L1e
        L1d:
            r0 = r2
        L1e:
            if (r0 == 0) goto L31
            com.hzbhd.ui.view.paged.LauncherScroller r0 = r4.mScroller
            if (r0 == 0) goto L2f
            int r3 = r4.getScrollY()
            int r0 = r0.getCurrY()
            if (r3 != r0) goto L2f
            r2 = r1
        L2f:
            if (r2 != 0) goto L55
        L31:
            boolean r0 = r4.mFreeScroll
            if (r0 == 0) goto L3a
            float r0 = r4.getScaleX()
            goto L3c
        L3a:
            r0 = 1065353216(0x3f800000, float:1.0)
        L3c:
            com.hzbhd.ui.view.paged.LauncherScroller r2 = r4.mScroller
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            int r2 = r2.getCurrX()
            float r2 = (float) r2
            float r3 = (float) r1
            float r3 = r3 / r0
            float r2 = r2 * r3
            int r0 = (int) r2
            com.hzbhd.ui.view.paged.LauncherScroller r2 = r4.mScroller
            if (r2 == 0) goto L55
            int r2 = r2.getCurrY()
            r4.scrollTo(r0, r2)
        L55:
            r4.invalidate()
            return r1
        L59:
            int r0 = r4.mNextPage
            r3 = -1
            if (r0 == r3) goto L79
            r4.sendScrollAccessibilityEvent()
            int r0 = r4.mNextPage
            int r0 = r4.validateNewPage(r0)
            r4.mCurrentPage = r0
            r4.mNextPage = r3
            r4.notifyPageSwitchListener()
            int r0 = r4.mTouchState
            if (r0 != 0) goto L75
            r4.pageEndMoving()
        L75:
            r4.onPostReorderingAnimationCompleted()
            return r1
        L79:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.ui.view.paged.PagedView.computeScrollHelper():boolean");
    }

    @Override // android.view.View
    public void computeScroll() {
        computeScrollHelper();
    }

    /* compiled from: PagedView.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u001b\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u0011\b\u0016\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/hzbhd/ui/view/paged/PagedView$LayoutParams;", "Landroid/view/ViewGroup$LayoutParams;", "width", "", "height", "(II)V", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", LcdInfoShare.DspInfo.source, "(Landroid/view/ViewGroup$LayoutParams;)V", "isFullScreenPage", "", "()Z", "setFullScreenPage", "(Z)V", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class LayoutParams extends ViewGroup.LayoutParams {
        private boolean isFullScreenPage;

        /* renamed from: isFullScreenPage, reason: from getter */
        public final boolean getIsFullScreenPage() {
            return this.isFullScreenPage;
        }

        public final void setFullScreenPage(boolean z) {
            this.isFullScreenPage = z;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        return new LayoutParams(getContext(), attrs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        Intrinsics.checkNotNullParameter(p, "p");
        return new LayoutParams(p);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        Intrinsics.checkNotNullParameter(p, "p");
        return p instanceof LayoutParams;
    }

    public final void addFullScreenPage(View page) {
        LayoutParams layoutParamsGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        layoutParamsGenerateDefaultLayoutParams.setFullScreenPage(true);
        super.addView(page, -1, layoutParamsGenerateDefaultLayoutParams);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int i2;
        int viewportWidth;
        int viewportHeight;
        int i3;
        if (getChildCount() == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int iMax = (int) (Math.max(displayMetrics.widthPixels + this.mInsets.left + this.mInsets.right, displayMetrics.heightPixels + this.mInsets.top + this.mInsets.bottom) * 2.0f);
        if (this.mUseMinScale) {
            float f = iMax;
            float f2 = this.mMinScale;
            i = (int) (f / f2);
            i2 = (int) (f / f2);
        } else {
            i = size;
            i2 = size2;
        }
        this.mViewport.set(0, 0, size, size2);
        if (mode == 0 || mode2 == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        if (size <= 0 || size2 <= 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        if (LogUtil.log3()) {
            LogUtil.d("PagedView.onMeasure(): " + size + ", " + size2);
        }
        if (LogUtil.log3()) {
            LogUtil.d("PagedView.scaledSize: " + i + ", " + i2);
        }
        if (LogUtil.log3()) {
            LogUtil.d("PagedView.parentSize: " + iMax + ", " + iMax);
        }
        if (LogUtil.log3()) {
            LogUtil.d("PagedView.horizontalPadding: " + paddingLeft);
        }
        if (LogUtil.log3()) {
            LogUtil.d("PagedView.verticalPadding: " + paddingTop);
        }
        int childCount = getChildCount();
        int i4 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View pageAt = getPageAt(i5);
            if (!(pageAt != null && pageAt.getVisibility() == 8)) {
                ViewGroup.LayoutParams layoutParams = pageAt != null ? pageAt.getLayoutParams() : null;
                Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type com.hzbhd.ui.view.paged.PagedView.LayoutParams");
                LayoutParams layoutParams2 = (LayoutParams) layoutParams;
                if (!layoutParams2.getIsFullScreenPage()) {
                    int i6 = layoutParams2.width == -2 ? Integer.MIN_VALUE : 1073741824;
                    i = layoutParams2.height == -2 ? Integer.MIN_VALUE : 1073741824;
                    viewportWidth = ((getViewportWidth() - paddingLeft) - this.mInsets.left) - this.mInsets.right;
                    viewportHeight = ((getViewportHeight() - paddingTop) - this.mInsets.top) - this.mInsets.bottom;
                    this.normalChildHeight = viewportHeight;
                    int i7 = i;
                    i = i6;
                    i3 = i7;
                } else {
                    viewportWidth = (getViewportWidth() - this.mInsets.left) - this.mInsets.right;
                    viewportHeight = getViewportHeight();
                    i3 = 1073741824;
                }
                if (i4 == 0) {
                    i4 = viewportWidth;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(viewportWidth, i);
                int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(viewportHeight, i3);
                if (pageAt != null) {
                    pageAt.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                }
            }
        }
        setMeasuredDimension(i, i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int i;
        int i2;
        int paddingTop;
        LayoutParams layoutParams;
        if (getChildCount() == 0) {
            return;
        }
        if (LogUtil.log5()) {
            LogUtil.d("PagedView.onLayout()");
        }
        int childCount = getChildCount();
        int viewportOffsetX = getViewportOffsetX();
        int viewportOffsetY = getViewportOffsetY();
        this.mViewport.offset(viewportOffsetX, viewportOffsetY);
        boolean z = this.mIsRtl;
        int i3 = z ? childCount - 1 : 0;
        int i4 = z ? -1 : childCount;
        int i5 = z ? -1 : 1;
        int paddingTop2 = getPaddingTop() + getPaddingBottom();
        ViewGroup.LayoutParams layoutParams2 = getChildAt(i3).getLayoutParams();
        Intrinsics.checkNotNull(layoutParams2, "null cannot be cast to non-null type com.hzbhd.ui.view.paged.PagedView.LayoutParams");
        int paddingLeft = (((LayoutParams) layoutParams2).getIsFullScreenPage() ? 0 : getPaddingLeft()) + viewportOffsetX;
        if (this.mPageScrolls == null || childCount != this.mChildCountOnLastLayout) {
            this.mPageScrolls = new int[childCount];
        }
        while (i3 != i4) {
            View pageAt = getPageAt(i3);
            if (pageAt != null && pageAt.getVisibility() == 8) {
                i2 = viewportOffsetY;
            } else {
                ViewGroup.LayoutParams layoutParams3 = pageAt != null ? pageAt.getLayoutParams() : null;
                Intrinsics.checkNotNull(layoutParams3, "null cannot be cast to non-null type com.hzbhd.ui.view.paged.PagedView.LayoutParams");
                LayoutParams layoutParams4 = (LayoutParams) layoutParams3;
                if (layoutParams4.getIsFullScreenPage()) {
                    paddingTop = viewportOffsetY;
                } else {
                    paddingTop = getPaddingTop() + viewportOffsetY + this.mInsets.top;
                    if (this.mCenterPagesVertically) {
                        paddingTop += ((((getViewportHeight() - this.mInsets.top) - this.mInsets.bottom) - paddingTop2) - pageAt.getMeasuredHeight()) / 2;
                    }
                }
                int measuredWidth = pageAt.getMeasuredWidth();
                int measuredHeight = pageAt.getMeasuredHeight();
                if (LogUtil.log5()) {
                    i2 = viewportOffsetY;
                    LogUtil.d("\tlayout-child" + i3 + ": " + paddingLeft + ", " + paddingTop);
                } else {
                    i2 = viewportOffsetY;
                }
                pageAt.layout(paddingLeft, paddingTop, pageAt.getMeasuredWidth() + paddingLeft, measuredHeight + paddingTop);
                int paddingLeft2 = layoutParams4.getIsFullScreenPage() ? 0 : getPaddingLeft();
                int[] iArr = this.mPageScrolls;
                Intrinsics.checkNotNull(iArr);
                iArr[i3] = (paddingLeft - paddingLeft2) - viewportOffsetX;
                int paddingRight = this.mPageSpacing;
                int i6 = i3 + i5;
                if (i6 != i4) {
                    View pageAt2 = getPageAt(i6);
                    ViewGroup.LayoutParams layoutParams5 = pageAt2 != null ? pageAt2.getLayoutParams() : null;
                    Intrinsics.checkNotNull(layoutParams5, "null cannot be cast to non-null type com.hzbhd.ui.view.paged.PagedView.LayoutParams");
                    layoutParams = (LayoutParams) layoutParams5;
                } else {
                    layoutParams = null;
                }
                if (layoutParams4.getIsFullScreenPage()) {
                    paddingRight = getPaddingLeft();
                } else if (layoutParams != null && layoutParams.getIsFullScreenPage()) {
                    paddingRight = getPaddingRight();
                }
                paddingLeft += measuredWidth + paddingRight + getChildGap();
            }
            i3 += i5;
            viewportOffsetY = i2;
        }
        LayoutTransition layoutTransition = getLayoutTransition();
        if (layoutTransition != null && layoutTransition.isRunning()) {
            layoutTransition.addTransitionListener(new LayoutTransition.TransitionListener() { // from class: com.hzbhd.ui.view.paged.PagedView.onLayout.1
                @Override // android.animation.LayoutTransition.TransitionListener
                public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                    Intrinsics.checkNotNullParameter(transition, "transition");
                    Intrinsics.checkNotNullParameter(container, "container");
                    Intrinsics.checkNotNullParameter(view, "view");
                }

                @Override // android.animation.LayoutTransition.TransitionListener
                public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                    Intrinsics.checkNotNullParameter(transition, "transition");
                    Intrinsics.checkNotNullParameter(container, "container");
                    Intrinsics.checkNotNullParameter(view, "view");
                    if (transition.isRunning()) {
                        return;
                    }
                    transition.removeTransitionListener(this);
                    PagedView.this.updateMaxScrollX();
                }
            });
        } else {
            updateMaxScrollX();
        }
        if (this.mFirstLayout && (i = this.mCurrentPage) >= 0 && i < childCount) {
            updateCurrentPageScroll();
            this.mFirstLayout = false;
        }
        LauncherScroller launcherScroller = this.mScroller;
        Intrinsics.checkNotNull(launcherScroller);
        if (launcherScroller.getIsFinished() && this.mChildCountOnLastLayout != childCount) {
            int i7 = this.restorePage;
            if (i7 != -1001) {
                setCurrentPage(i7);
                this.restorePage = INVALID_RESTORE_PAGE;
            } else {
                setCurrentPage(getNextPage());
            }
        }
        this.mChildCountOnLastLayout = childCount;
        if (isReordering(true)) {
            updateDragViewTranslationDuringDrag();
        }
    }

    public final void updateMaxScrollX() {
        int childCount = getChildCount();
        if (childCount > 0) {
            scrollForPage = getScrollForPage(this.mIsRtl ? 0 : childCount - 1);
        }
        this.mMaxScrollX = scrollForPage;
    }

    public final void setPageSpacing(int pageSpacing) {
        this.mPageSpacing = pageSpacing;
        requestLayout();
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public void onChildViewAdded(View parent, View child) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(child, "child");
        updatePageIndicator(getNextPage(), getChildCount());
        this.mForceScreenScrolled = true;
        updateFreescrollBounds();
        invalidate();
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public void onChildViewRemoved(View parent, View child) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(child, "child");
        this.mForceScreenScrolled = true;
        updateFreescrollBounds();
        invalidate();
    }

    private final void removeMarkerForView(int index) {
        updatePageIndicator(index, getChildCount());
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        removeMarkerForView(indexOfChild(v));
        super.removeView(v);
    }

    @Override // android.view.ViewGroup
    public void removeViewInLayout(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        removeMarkerForView(indexOfChild(v));
        super.removeViewInLayout(v);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int index) {
        removeMarkerForView(index);
        super.removeViewAt(index);
    }

    @Override // android.view.ViewGroup
    public void removeAllViewsInLayout() {
        updatePageIndicator(0, getChildCount());
        super.removeAllViewsInLayout();
    }

    protected final int getChildOffset(int index) {
        if (index < 0 || index > getChildCount() - 1) {
            return 0;
        }
        View pageAt = getPageAt(index);
        Intrinsics.checkNotNull(pageAt);
        return pageAt.getLeft() - getViewportOffsetX();
    }

    protected final void getFreeScrollPageRange(int[] range) {
        Intrinsics.checkNotNullParameter(range, "range");
        range[0] = 0;
        range[1] = Math.max(0, getChildCount() - 1);
    }

    protected final void getVisiblePages(int[] range) {
        Intrinsics.checkNotNullParameter(range, "range");
        int childCount = getChildCount();
        int[] iArr = sTmpIntPoint;
        iArr[1] = 0;
        iArr[0] = 0;
        range[0] = -1;
        range[1] = -1;
        if (childCount > 0) {
            int viewportWidth = getViewportWidth();
            int childCount2 = getChildCount();
            int i = 0;
            for (int i2 = 0; i2 < childCount2; i2++) {
                View pageAt = getPageAt(i2);
                int[] iArr2 = sTmpIntPoint;
                iArr2[0] = 0;
                if (viewportWidth < 0) {
                    if (range[0] != -1) {
                        break;
                    }
                } else {
                    Intrinsics.checkNotNull(pageAt);
                    int measuredWidth = pageAt.getMeasuredWidth();
                    iArr2[0] = measuredWidth;
                    if (measuredWidth < 0) {
                        if (range[0] != -1) {
                            break;
                        }
                    } else {
                        if (range[0] < 0) {
                            range[0] = i2;
                        }
                        i = i2;
                    }
                }
            }
            range[1] = i;
            return;
        }
        range[0] = -1;
        range[1] = -1;
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {
        Intrinsics.checkNotNullParameter(changedView, "changedView");
        super.onVisibilityChanged(changedView, visibility);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setVisibility(visibility);
        }
        updatePageIndicator();
    }

    protected final boolean shouldDrawChild(View child) {
        Intrinsics.checkNotNullParameter(child, "child");
        return child.getVisibility() == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0081  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void dispatchDraw(android.graphics.Canvas r14) {
        /*
            r13 = this;
            java.lang.String r0 = "canvas"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            int r0 = r13.getChildCount()
            if (r0 <= 0) goto L93
            int r1 = r13.getViewportWidth()
            int r1 = r1 / 2
            int r2 = r13.getScrollX()
            int r2 = r2 + r1
            int r1 = r13.mLastScreenCenter
            r3 = 0
            if (r2 != r1) goto L1f
            boolean r1 = r13.mForceScreenScrolled
            if (r1 == 0) goto L26
        L1f:
            r13.mForceScreenScrolled = r3
            r13.screenScrolled(r2)
            r13.mLastScreenCenter = r2
        L26:
            int[] r1 = r13.mTempVisiblePagesRange
            r13.getVisiblePages(r1)
            int[] r1 = r13.mTempVisiblePagesRange
            r2 = r1[r3]
            r4 = 1
            r1 = r1[r4]
            r5 = -1
            if (r2 == r5) goto L93
            if (r1 == r5) goto L93
            long r6 = r13.getDrawingTime()
            r14.save()
            int r8 = r13.getScrollX()
            int r9 = r13.getScrollY()
            int r10 = r13.getScrollX()
            int r11 = r13.getRight()
            int r10 = r10 + r11
            int r11 = r13.getLeft()
            int r10 = r10 - r11
            int r11 = r13.getScrollY()
            int r12 = r13.getBottom()
            int r11 = r11 + r12
            int r12 = r13.getTop()
            int r11 = r11 - r12
            r14.clipRect(r8, r9, r10, r11)
            int r0 = r0 - r4
        L66:
            if (r5 >= r0) goto L87
            android.view.View r4 = r13.getPageAt(r0)
            android.view.View r8 = r13.mDragView
            if (r4 == r8) goto L84
            boolean r8 = r13.mForceDrawAllChildrenNextFrame
            if (r8 != 0) goto L81
            if (r2 > r0) goto L84
            if (r0 > r1) goto L84
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            boolean r8 = r13.shouldDrawChild(r4)
            if (r8 == 0) goto L84
        L81:
            r13.drawChild(r14, r4, r6)
        L84:
            int r0 = r0 + (-1)
            goto L66
        L87:
            android.view.View r0 = r13.mDragView
            if (r0 == 0) goto L8e
            r13.drawChild(r14, r0, r6)
        L8e:
            r13.mForceDrawAllChildrenNextFrame = r3
            r14.restore()
        L93:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.ui.view.paged.PagedView.dispatchDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) {
        Intrinsics.checkNotNullParameter(child, "child");
        Intrinsics.checkNotNullParameter(rectangle, "rectangle");
        int iIndexToPage = indexToPage(indexOfChild(child));
        if (iIndexToPage == this.mCurrentPage) {
            LauncherScroller launcherScroller = this.mScroller;
            Intrinsics.checkNotNull(launcherScroller);
            if (launcherScroller.getIsFinished()) {
                return false;
            }
        }
        snapToPage(iIndexToPage);
        return true;
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        int i = this.mNextPage;
        if (i == -1) {
            i = this.mCurrentPage;
        }
        View pageAt = getPageAt(i);
        if (pageAt != null) {
            return pageAt.requestFocus(direction, previouslyFocusedRect);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchUnhandledMove(View focused, int direction) {
        Intrinsics.checkNotNullParameter(focused, "focused");
        if (direction == 17) {
            if (getMCurrentPage() > 0) {
                snapToPage(getMCurrentPage() - 1);
                return true;
            }
        } else if (direction == 66 && getMCurrentPage() < getPageCount() - 1) {
            snapToPage(getMCurrentPage() + 1);
            return true;
        }
        return super.dispatchUnhandledMove(focused, direction);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        View pageAt;
        View pageAt2;
        View pageAt3;
        Intrinsics.checkNotNullParameter(views, "views");
        int i = this.mCurrentPage;
        if (i >= 0 && i < getPageCount() && (pageAt3 = getPageAt(this.mCurrentPage)) != null) {
            pageAt3.addFocusables(views, direction, focusableMode);
        }
        if (direction == 17) {
            int i2 = this.mCurrentPage;
            if (i2 <= 0 || (pageAt = getPageAt(i2 - 1)) == null) {
                return;
            }
            pageAt.addFocusables(views, direction, focusableMode);
            return;
        }
        if (direction == 66 && this.mCurrentPage < getPageCount() - 1 && (pageAt2 = getPageAt(this.mCurrentPage + 1)) != null) {
            pageAt2.addFocusables(views, direction, focusableMode);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void focusableViewAvailable(View focused) {
        Intrinsics.checkNotNullParameter(focused, "focused");
        View pageAt = getPageAt(this.mCurrentPage);
        View view = focused;
        while (view != pageAt) {
            if (view == this || !(view.getParent() instanceof View)) {
                return;
            }
            Object parent = view.getParent();
            Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
            view = (View) parent;
        }
        super.focusableViewAvailable(focused);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        View pageAt;
        if (disallowIntercept && (pageAt = getPageAt(this.mCurrentPage)) != null) {
            pageAt.cancelLongPress();
        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    protected final boolean hitsPreviousPage(float x, float y) {
        if (this.mIsRtl) {
            if (x > ((getViewportOffsetX() + getViewportWidth()) - getPaddingRight()) - this.mPageSpacing) {
                return true;
            }
        } else if (x < getViewportOffsetX() + getPaddingLeft() + this.mPageSpacing) {
            return true;
        }
        return false;
    }

    protected final boolean hitsNextPage(float x, float y) {
        if (this.mIsRtl) {
            if (x < getViewportOffsetX() + getPaddingLeft() + this.mPageSpacing) {
                return true;
            }
        } else if (x > ((getViewportOffsetX() + getViewportWidth()) - getPaddingRight()) - this.mPageSpacing) {
            return true;
        }
        return false;
    }

    private final boolean isTouchPointInViewportWithBuffer(int x, int y) {
        Rect rect = sTmpRect;
        rect.set(this.mViewport.left - (this.mViewport.width() / 2), this.mViewport.top, this.mViewport.right + (this.mViewport.width() / 2), this.mViewport.bottom);
        return rect.contains(x, y);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0045  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            java.lang.String r0 = "ev"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            r7.acquireVelocityTrackerAndAddMovement(r8)
            int r0 = r7.getChildCount()
            if (r0 > 0) goto L13
            boolean r8 = super.onInterceptTouchEvent(r8)
            return r8
        L13:
            int r0 = r8.getAction()
            r1 = 2
            r2 = 1
            if (r0 != r1) goto L20
            int r3 = r7.mTouchState
            if (r3 != r2) goto L20
            return r2
        L20:
            r0 = r0 & 255(0xff, float:3.57E-43)
            r3 = 3
            r4 = 0
            r5 = 0
            if (r0 == 0) goto L4a
            if (r0 == r2) goto L45
            if (r0 == r1) goto L3a
            if (r0 == r3) goto L45
            r1 = 6
            if (r0 == r1) goto L32
            goto Lcf
        L32:
            r7.onSecondaryPointerUp(r8)
            r7.releaseVelocityTracker()
            goto Lcf
        L3a:
            int r0 = r7.mActivePointerId
            r3 = -1
            if (r0 == r3) goto Lcf
            r0 = 0
            determineScrollingStart$default(r7, r8, r4, r1, r0)
            goto Lcf
        L45:
            r7.resetTouchState()
            goto Lcf
        L4a:
            float r0 = r8.getX()
            float r1 = r8.getY()
            r7.mDownMotionX = r0
            r7.mDownMotionY = r1
            int r6 = r7.getScrollX()
            float r6 = (float) r6
            r7.mDownScrollX = r6
            r7.mLastMotionX = r0
            r7.mLastMotionY = r1
            r6 = r7
            android.view.View r6 = (android.view.View) r6
            float[] r0 = r7.mapPointFromViewToParent(r6, r0, r1)
            r1 = r0[r5]
            r7.mParentDownMotionX = r1
            r0 = r0[r2]
            r7.mParentDownMotionY = r0
            r7.mLastMotionXRemainder = r4
            r7.mTotalMotionX = r4
            int r8 = r8.getPointerId(r5)
            r7.mActivePointerId = r8
            com.hzbhd.ui.view.paged.LauncherScroller r8 = r7.mScroller
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            int r8 = r8.getMFinalX()
            com.hzbhd.ui.view.paged.LauncherScroller r0 = r7.mScroller
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            int r0 = r0.getCurrX()
            int r8 = r8 - r0
            int r8 = java.lang.Math.abs(r8)
            com.hzbhd.ui.view.paged.LauncherScroller r0 = r7.mScroller
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            boolean r0 = r0.getIsFinished()
            if (r0 != 0) goto La4
            int r0 = r7.mTouchSlop
            int r0 = r0 / r3
            if (r8 >= r0) goto La2
            goto La4
        La2:
            r8 = r5
            goto La5
        La4:
            r8 = r2
        La5:
            if (r8 == 0) goto Lc3
            r7.mTouchState = r5
            com.hzbhd.ui.view.paged.LauncherScroller r8 = r7.mScroller
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            boolean r8 = r8.getIsFinished()
            if (r8 != 0) goto Lcf
            boolean r8 = r7.mFreeScroll
            if (r8 != 0) goto Lcf
            int r8 = r7.getNextPage()
            r7.setCurrentPage(r8)
            r7.pageEndMoving()
            goto Lcf
        Lc3:
            float r8 = r7.mDownMotionX
            int r8 = (int) r8
            float r0 = r7.mDownMotionY
            int r0 = (int) r0
            boolean r8 = r7.isTouchPointInViewportWithBuffer(r8, r0)
            r7.mTouchState = r8
        Lcf:
            int r8 = r7.mTouchState
            if (r8 == 0) goto Ld4
            goto Ld5
        Ld4:
            r2 = r5
        Ld5:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.ui.view.paged.PagedView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public static /* synthetic */ void determineScrollingStart$default(PagedView pagedView, MotionEvent motionEvent, float f, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: determineScrollingStart");
        }
        if ((i & 2) != 0) {
            f = MAX_SCROLL_PROGRESS;
        }
        pagedView.determineScrollingStart(motionEvent, f);
    }

    protected final void determineScrollingStart(MotionEvent ev, float touchSlopScale) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        int iFindPointerIndex = ev.findPointerIndex(this.mActivePointerId);
        if (iFindPointerIndex == -1) {
            return;
        }
        float x = ev.getX(iFindPointerIndex);
        if (isTouchPointInViewportWithBuffer((int) x, (int) ev.getY(iFindPointerIndex))) {
            if (((int) Math.abs(x - this.mLastMotionX)) > Math.round(touchSlopScale * ((float) this.mTouchSlop))) {
                this.mTouchState = 1;
                this.mTotalMotionX += Math.abs(this.mLastMotionX - x);
                this.mLastMotionX = x;
                this.mLastMotionXRemainder = 0.0f;
                this.mTouchX = getViewportOffsetX() + getScrollX();
                this.mSmoothingTime = System.nanoTime() / NANOTIME_DIV;
                onScrollInteractionBegin();
                pageBeginMoving();
            }
        }
    }

    protected final void cancelCurrentPageLongPress() {
        View pageAt = getPageAt(this.mCurrentPage);
        if (pageAt != null) {
            pageAt.cancelLongPress();
        }
    }

    protected final float getScrollProgress(int screenCenter, View v, int page) {
        int measuredWidth;
        Intrinsics.checkNotNullParameter(v, "v");
        int scrollForPage = screenCenter - (getScrollForPage(page) + (getViewportWidth() / 2));
        int childCount = getChildCount();
        int i = page + 1;
        if ((scrollForPage < 0 && !this.mIsRtl) || (scrollForPage > 0 && this.mIsRtl)) {
            i = page - 1;
        }
        if (i < 0 || i > childCount - 1) {
            measuredWidth = v.getMeasuredWidth() + this.mPageSpacing;
        } else {
            measuredWidth = Math.abs(getScrollForPage(i) - getScrollForPage(page));
        }
        return Math.max(Math.min(scrollForPage / (measuredWidth * MAX_SCROLL_PROGRESS), MAX_SCROLL_PROGRESS), -1.0f);
    }

    public final int getScrollForPage(int index) {
        int[] iArr = this.mPageScrolls;
        if (iArr != null) {
            Intrinsics.checkNotNull(iArr);
            if (index < iArr.length && index >= 0) {
                int[] iArr2 = this.mPageScrolls;
                Intrinsics.checkNotNull(iArr2);
                return iArr2[index];
            }
        }
        return 0;
    }

    public final int getLayoutTransitionOffsetForPage(int index) {
        int[] iArr = this.mPageScrolls;
        int paddingRight = 0;
        if (iArr == null) {
            return 0;
        }
        Intrinsics.checkNotNull(iArr);
        if (index >= iArr.length || index < 0) {
            return 0;
        }
        View childAt = getChildAt(index);
        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type com.hzbhd.ui.view.paged.PagedView.LayoutParams");
        if (!((LayoutParams) layoutParams).getIsFullScreenPage()) {
            paddingRight = this.mIsRtl ? getPaddingRight() : getPaddingLeft();
        }
        Intrinsics.checkNotNull(this.mPageScrolls);
        return (int) (childAt.getX() - ((r2[index] + paddingRight) + getViewportOffsetX()));
    }

    public final void enableFreeScroll() {
        setEnableFreeScroll(true);
    }

    public final void disableFreeScroll() {
        setEnableFreeScroll(false);
    }

    public final void updateFreescrollBounds() {
        getFreeScrollPageRange(this.mTempVisiblePagesRange);
        if (this.mIsRtl) {
            this.mFreeScrollMinScrollX = getScrollForPage(this.mTempVisiblePagesRange[1]);
            this.mFreeScrollMaxScrollX = getScrollForPage(this.mTempVisiblePagesRange[0]);
        } else {
            this.mFreeScrollMinScrollX = getScrollForPage(this.mTempVisiblePagesRange[0]);
            this.mFreeScrollMaxScrollX = getScrollForPage(this.mTempVisiblePagesRange[1]);
        }
    }

    private final void setEnableFreeScroll(boolean freeScroll) {
        this.mFreeScroll = freeScroll;
        if (freeScroll) {
            updateFreescrollBounds();
            getFreeScrollPageRange(this.mTempVisiblePagesRange);
            int mCurrentPage = getMCurrentPage();
            int i = this.mTempVisiblePagesRange[0];
            if (mCurrentPage < i) {
                setCurrentPage(i);
            } else {
                int mCurrentPage2 = getMCurrentPage();
                int i2 = this.mTempVisiblePagesRange[1];
                if (mCurrentPage2 > i2) {
                    setCurrentPage(i2);
                }
            }
        }
        setEnableOverscroll(!freeScroll);
    }

    protected final void setEnableOverscroll(boolean enable) {
        this.mAllowOverScroll = enable;
    }

    private final int getNearestHoverOverPageIndex() {
        View view = this.mDragView;
        if (view == null) {
            return -1;
        }
        Intrinsics.checkNotNull(view);
        int left = view.getLeft();
        View view2 = this.mDragView;
        Intrinsics.checkNotNull(view2);
        float measuredWidth = left + (view2.getMeasuredWidth() / 2);
        View view3 = this.mDragView;
        Intrinsics.checkNotNull(view3);
        int translationX = (int) (measuredWidth + view3.getTranslationX());
        getFreeScrollPageRange(this.mTempVisiblePagesRange);
        int i = Integer.MAX_VALUE;
        int iIndexOfChild = indexOfChild(this.mDragView);
        int[] iArr = this.mTempVisiblePagesRange;
        int i2 = iArr[0];
        int i3 = iArr[1];
        if (i2 <= i3) {
            while (true) {
                View pageAt = getPageAt(i2);
                Intrinsics.checkNotNull(pageAt);
                int iAbs = Math.abs(translationX - (pageAt.getLeft() + (pageAt.getMeasuredWidth() / 2)));
                if (iAbs < i) {
                    iIndexOfChild = i2;
                    i = iAbs;
                }
                if (i2 == i3) {
                    break;
                }
                i2++;
            }
        }
        return iIndexOfChild;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x023e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0242 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0227 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x022b A[ADDED_TO_REGION] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r14) {
        /*
            Method dump skipped, instructions count: 851
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.ui.view.paged.PagedView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onTouchEvent$lambda-3, reason: not valid java name */
    public static final void m1213onTouchEvent$lambda3(PagedView this$0, int i, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.snapToPage(i);
        int i3 = i2 < i ? -1 : 1;
        int i4 = i2 > i ? i2 - 1 : i;
        for (int i5 = i2 < i ? i2 + 1 : i; i5 <= i4; i5++) {
            View childAt = this$0.getChildAt(i5);
            int viewportOffsetX = this$0.getViewportOffsetX() + this$0.getChildOffset(i5);
            int viewportOffsetX2 = this$0.getViewportOffsetX() + this$0.getChildOffset(i5 + i3);
            Object tag = childAt.getTag(100);
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type android.animation.AnimatorSet");
            ((AnimatorSet) tag).cancel();
            childAt.setTranslationX(viewportOffsetX - viewportOffsetX2);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(REORDERING_REORDER_REPOSITION_DURATION);
            animatorSet.playTogether(ObjectAnimator.ofFloat(childAt, "translationX", 0.0f));
            animatorSet.start();
            childAt.setTag(animatorSet);
        }
        View view = this$0.mDragView;
        Intrinsics.checkNotNull(view);
        this$0.removeView(view);
        this$0.addView(this$0.mDragView, i);
        this$0.mSidePageHoverIndex = -1;
        this$0.updatePageIndicator(this$0.getNextPage(), this$0.getChildCount());
    }

    private final void resetTouchState() {
        releaseVelocityTracker();
        endReordering();
        this.mCancelTap = false;
        this.mTouchState = 0;
        this.mActivePointerId = -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
    
        if ((r2 == 0.0f) == false) goto L20;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onGenericMotionEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            int r0 = r9.getSource()
            r0 = r0 & 2
            if (r0 == 0) goto L65
            int r0 = r9.getAction()
            r1 = 8
            if (r0 != r1) goto L65
            int r0 = r9.getMetaState()
            r1 = 1
            r0 = r0 & r1
            r2 = 9
            r3 = 0
            if (r0 == 0) goto L26
            float r0 = r9.getAxisValue(r2)
            r2 = r3
            goto L34
        L26:
            float r0 = r9.getAxisValue(r2)
            float r0 = -r0
            r2 = 10
            float r2 = r9.getAxisValue(r2)
            r7 = r2
            r2 = r0
            r0 = r7
        L34:
            int r4 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            r5 = 0
            if (r4 != 0) goto L3b
            r6 = r1
            goto L3c
        L3b:
            r6 = r5
        L3c:
            if (r6 == 0) goto L47
            int r6 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r6 != 0) goto L44
            r6 = r1
            goto L45
        L44:
            r6 = r5
        L45:
            if (r6 != 0) goto L65
        L47:
            boolean r9 = r8.mIsRtl
            if (r9 == 0) goto L52
            if (r4 < 0) goto L5a
            int r9 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r9 >= 0) goto L5b
            goto L5a
        L52:
            int r9 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r9 > 0) goto L5a
            int r9 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r9 <= 0) goto L5b
        L5a:
            r5 = r1
        L5b:
            if (r5 == 0) goto L61
            r8.scrollRight()
            goto L64
        L61:
            r8.scrollLeft()
        L64:
            return r1
        L65:
            boolean r9 = super.onGenericMotionEvent(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.ui.view.paged.PagedView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    private final void acquireVelocityTrackerAndAddMovement(MotionEvent ev) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        VelocityTracker velocityTracker = this.mVelocityTracker;
        Intrinsics.checkNotNull(velocityTracker);
        velocityTracker.addMovement(ev);
    }

    private final void releaseVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            Intrinsics.checkNotNull(velocityTracker);
            velocityTracker.clear();
            VelocityTracker velocityTracker2 = this.mVelocityTracker;
            Intrinsics.checkNotNull(velocityTracker2);
            velocityTracker2.recycle();
            this.mVelocityTracker = null;
        }
    }

    private final void onSecondaryPointerUp(MotionEvent ev) {
        int action = (ev.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (ev.getPointerId(action) == this.mActivePointerId) {
            int i = action == 0 ? 1 : 0;
            float x = ev.getX(i);
            this.mDownMotionX = x;
            this.mLastMotionX = x;
            this.mLastMotionY = ev.getY(i);
            this.mLastMotionXRemainder = 0.0f;
            this.mActivePointerId = ev.getPointerId(i);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                Intrinsics.checkNotNull(velocityTracker);
                velocityTracker.clear();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View child, View focused) {
        Intrinsics.checkNotNullParameter(child, "child");
        Intrinsics.checkNotNullParameter(focused, "focused");
        super.requestChildFocus(child, focused);
        int iIndexToPage = indexToPage(indexOfChild(child));
        if (iIndexToPage < 0 || iIndexToPage == getMCurrentPage() || isInTouchMode()) {
            return;
        }
        snapToPage(iIndexToPage);
    }

    public final int getPageNearestToCenterOfScreen() {
        int viewportOffsetX = getViewportOffsetX() + getScrollX() + (getViewportWidth() / 2);
        int childCount = getChildCount();
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        for (int i3 = 0; i3 < childCount; i3++) {
            View pageAt = getPageAt(i3);
            Intrinsics.checkNotNull(pageAt);
            int iAbs = Math.abs(((getViewportOffsetX() + getChildOffset(i3)) + (pageAt.getMeasuredWidth() / 2)) - viewportOffsetX);
            if (iAbs < i) {
                i2 = i3;
                i = iAbs;
            }
        }
        return i2;
    }

    protected final void snapToDestination() {
        snapToPage$default(this, getPageNearestToCenterOfScreen(), 300, false, null, 12, null);
    }

    private final float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((f - 0.5f) * 0.4712389f);
    }

    protected final void snapToPageWithVelocity(int whichPage, int velocity) {
        int iValidateNewPage = validateNewPage(whichPage);
        int viewportWidth = getViewportWidth() / 2;
        int scrollForPage = getScrollForPage(iValidateNewPage) - getScrollX();
        if (Math.abs(velocity) < this.mMinFlingVelocity) {
            snapToPage$default(this, iValidateNewPage, 300, false, null, 12, null);
            return;
        }
        float fMin = Math.min(MAX_SCROLL_PROGRESS, (Math.abs(scrollForPage) * MAX_SCROLL_PROGRESS) / (viewportWidth * 2));
        float f = viewportWidth;
        snapToPage$default(this, iValidateNewPage, scrollForPage, Math.round(1000 * Math.abs((f + (distanceInfluenceForSnapDuration(fMin) * f)) / Math.max(this.mMinSnapVelocity, Math.abs(velocity)))) * 2, false, null, 24, null);
    }

    public final void snapToPage(int whichPage) {
        snapToPage$default(this, whichPage, 300, false, null, 12, null);
    }

    public final void snapToPageImmediately(int whichPage) {
        snapToPage(whichPage, 300, true, null);
    }

    protected final void snapToPage(int whichPage, int duration, TimeInterpolator interpolator) {
        snapToPage(whichPage, duration, false, interpolator);
    }

    public static /* synthetic */ void snapToPage$default(PagedView pagedView, int i, int i2, boolean z, TimeInterpolator timeInterpolator, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: snapToPage");
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            timeInterpolator = null;
        }
        pagedView.snapToPage(i, i2, z, timeInterpolator);
    }

    protected final void snapToPage(int whichPage, int duration, boolean immediate, TimeInterpolator interpolator) {
        int iValidateNewPage = validateNewPage(whichPage);
        snapToPage(iValidateNewPage, getScrollForPage(iValidateNewPage) - getScrollX(), duration, immediate, interpolator);
    }

    public static /* synthetic */ void snapToPage$default(PagedView pagedView, int i, int i2, int i3, boolean z, TimeInterpolator timeInterpolator, int i4, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: snapToPage");
        }
        if ((i4 & 8) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i4 & 16) != 0) {
            timeInterpolator = null;
        }
        pagedView.snapToPage(i, i2, i3, z2, timeInterpolator);
    }

    protected final void snapToPage(int whichPage, int delta, int duration, boolean immediate, TimeInterpolator interpolator) {
        int i;
        int i2;
        int iValidateNewPage = validateNewPage(whichPage);
        this.mNextPage = iValidateNewPage;
        View focusedChild = getFocusedChild();
        if (focusedChild != null && iValidateNewPage != (i2 = this.mCurrentPage) && focusedChild == getPageAt(i2)) {
            focusedChild.clearFocus();
        }
        pageBeginMoving();
        awakenScrollBars(duration);
        if (immediate) {
            i = 0;
        } else {
            if (duration == 0) {
                duration = Math.abs(delta);
            }
            i = duration;
        }
        LauncherScroller launcherScroller = this.mScroller;
        Intrinsics.checkNotNull(launcherScroller);
        if (!launcherScroller.getIsFinished()) {
            abortScrollerAnimation(false);
        }
        if (interpolator != null) {
            LauncherScroller launcherScroller2 = this.mScroller;
            Intrinsics.checkNotNull(launcherScroller2);
            launcherScroller2.setInterpolator(interpolator);
        } else {
            LauncherScroller launcherScroller3 = this.mScroller;
            Intrinsics.checkNotNull(launcherScroller3);
            launcherScroller3.setInterpolator(this.mDefaultInterpolator);
        }
        LauncherScroller launcherScroller4 = this.mScroller;
        Intrinsics.checkNotNull(launcherScroller4);
        launcherScroller4.startScroll(getScrollX(), 0, delta, 0, i);
        updatePageIndicator();
        if (immediate) {
            computeScroll();
        }
        this.mForceScreenScrolled = true;
        invalidate();
    }

    public final void scrollLeft() {
        if (getNextPage() > 0) {
            snapToPage(getNextPage() - 1);
        }
    }

    public final boolean scrollRight() {
        if (getNextPage() >= getChildCount() - 1) {
            return false;
        }
        snapToPage(getNextPage() + 1);
        return true;
    }

    public final int getPageForView(View v) {
        if (v == null) {
            return -1;
        }
        ViewParent parent = v.getParent();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (parent == getPageAt(i)) {
                return i;
            }
        }
        return -1;
    }

    @Override // android.view.View
    public boolean performLongClick() {
        this.mCancelTap = true;
        return super.performLongClick();
    }

    /* compiled from: PagedView.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0011\b\u0010\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0010\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\tH\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/hzbhd/ui/view/paged/PagedView$SavedState;", "Landroid/view/View$BaseSavedState;", "superState", "Landroid/os/Parcelable;", "(Landroid/os/Parcelable;)V", "in", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "currentPage", "", "getCurrentPage", "()I", "setCurrentPage", "(I)V", "writeToParcel", "", "out", "flags", "Companion", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class SavedState extends View.BaseSavedState {
        private int currentPage;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.hzbhd.ui.view.paged.PagedView$SavedState$Companion$CREATOR$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PagedView.SavedState createFromParcel(Parcel in) {
                Intrinsics.checkNotNullParameter(in, "in");
                return new PagedView.SavedState(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PagedView.SavedState[] newArray(int size) {
                return new PagedView.SavedState[size];
            }
        };

        public final int getCurrentPage() {
            return this.currentPage;
        }

        public final void setCurrentPage(int i) {
            this.currentPage = i;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
            this.currentPage = -1;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SavedState(Parcel in) {
            super(in);
            Intrinsics.checkNotNullParameter(in, "in");
            this.currentPage = -1;
            this.currentPage = in.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            Intrinsics.checkNotNullParameter(out, "out");
            super.writeToParcel(out, flags);
            out.writeInt(this.currentPage);
        }

        /* compiled from: PagedView.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/hzbhd/ui/view/paged/PagedView$SavedState$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/hzbhd/ui/view/paged/PagedView$SavedState;", "getCREATOR", "()Landroid/os/Parcelable$Creator;", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Parcelable.Creator<SavedState> getCREATOR() {
                return SavedState.CREATOR;
            }
        }
    }

    private final void animateDragViewToOriginalPosition() {
        if (this.mDragView != null) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(200L);
            animatorSet.playTogether(ObjectAnimator.ofFloat(this.mDragView, "translationX", 0.0f), ObjectAnimator.ofFloat(this.mDragView, "translationY", 0.0f), ObjectAnimator.ofFloat(this.mDragView, "scaleX", MAX_SCROLL_PROGRESS), ObjectAnimator.ofFloat(this.mDragView, "scaleY", MAX_SCROLL_PROGRESS));
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.hzbhd.ui.view.paged.PagedView.animateDragViewToOriginalPosition.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    PagedView.this.onPostReorderingAnimationCompleted();
                }
            });
            animatorSet.start();
        }
    }

    public final void onStartReordering() {
        this.mTouchState = 4;
        this.mIsReordering = true;
        invalidate();
    }

    public final void onPostReorderingAnimationCompleted() {
        int i = this.mPostReorderingPreZoomInRemainingAnimationCount - 1;
        this.mPostReorderingPreZoomInRemainingAnimationCount = i;
        Runnable runnable = this.mPostReorderingPreZoomInRunnable;
        if (runnable == null || i != 0) {
            return;
        }
        Intrinsics.checkNotNull(runnable);
        runnable.run();
        this.mPostReorderingPreZoomInRunnable = null;
    }

    public final void onEndReordering() {
        this.mIsReordering = false;
    }

    public final boolean startReordering(View v) {
        int iIndexOfChild = indexOfChild(v);
        if (this.mTouchState == 0 && iIndexOfChild != -1) {
            int[] iArr = this.mTempVisiblePagesRange;
            iArr[0] = 0;
            iArr[1] = getPageCount() - 1;
            getFreeScrollPageRange(this.mTempVisiblePagesRange);
            this.mReorderingStarted = true;
            int[] iArr2 = this.mTempVisiblePagesRange;
            if (iArr2[0] <= iIndexOfChild && iIndexOfChild <= iArr2[1]) {
                View childAt = getChildAt(iIndexOfChild);
                this.mDragView = childAt;
                Intrinsics.checkNotNull(childAt);
                childAt.animate().scaleX(1.15f).scaleY(1.15f).setDuration(100L).start();
                Intrinsics.checkNotNull(this.mDragView);
                this.mDragViewBaselineLeft = r5.getLeft();
                snapToPage(getPageNearestToCenterOfScreen());
                disableFreeScroll();
                onStartReordering();
                return true;
            }
        }
        return false;
    }

    public final boolean isReordering(boolean testTouchState) {
        boolean z = this.mIsReordering;
        if (testTouchState) {
            return z & (this.mTouchState == 4);
        }
        return z;
    }

    public final void endReordering() {
        if (this.mReorderingStarted) {
            this.mReorderingStarted = false;
            final Runnable runnable = new Runnable() { // from class: com.hzbhd.ui.view.paged.PagedView$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    PagedView.m1211endReordering$lambda4(this.f$0);
                }
            };
            this.mPostReorderingPreZoomInRunnable = new Runnable() { // from class: com.hzbhd.ui.view.paged.PagedView$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    PagedView.m1212endReordering$lambda5(runnable, this);
                }
            };
            this.mPostReorderingPreZoomInRemainingAnimationCount = this.NUM_ANIMATIONS_RUNNING_BEFORE_ZOOM_OUT;
            snapToPage$default(this, indexOfChild(this.mDragView), 0, false, null, 12, null);
            animateDragViewToOriginalPosition();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: endReordering$lambda-4, reason: not valid java name */
    public static final void m1211endReordering$lambda4(PagedView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onEndReordering();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: endReordering$lambda-5, reason: not valid java name */
    public static final void m1212endReordering$lambda5(Runnable onCompleteRunnable, PagedView this$0) {
        Intrinsics.checkNotNullParameter(onCompleteRunnable, "$onCompleteRunnable");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        onCompleteRunnable.run();
        this$0.enableFreeScroll();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        Intrinsics.checkNotNullParameter(info, "info");
        super.onInitializeAccessibilityNodeInfo(info);
        info.setScrollable(getPageCount() > 1);
        if (getMCurrentPage() < getPageCount() - 1) {
            info.addAction(4096);
        }
        if (getMCurrentPage() > 0) {
            info.addAction(8192);
        }
        info.setClassName(getClass().getName());
        info.setLongClickable(false);
        info.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_LONG_CLICK);
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEvent(int eventType) {
        if (eventType != 4096) {
            super.sendAccessibilityEvent(eventType);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        super.onInitializeAccessibilityEvent(event);
        event.setScrollable(getPageCount() > 1);
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int action, Bundle arguments) {
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        if (super.performAccessibilityAction(action, arguments)) {
            return true;
        }
        if (action == 4096) {
            if (getMCurrentPage() >= getPageCount() - 1) {
                return false;
            }
            scrollRight();
            return true;
        }
        if (action != 8192 || getMCurrentPage() <= 0) {
            return false;
        }
        scrollLeft();
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Intrinsics.checkNotNullParameter(ev, "ev");
        if (ev.getAction() == 0) {
            enableScroll();
        }
        if (this.mScrollAble) {
            return super.dispatchTouchEvent(ev);
        }
        return true;
    }

    public final void enableScroll() {
        this.mScrollAble = true;
    }

    public final void didAbleScroll() {
        this.mScrollAble = false;
    }

    /* compiled from: PagedView.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0006X\u0084T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/hzbhd/ui/view/paged/PagedView$Companion;", "", "()V", "ALPHA_QUANTIZE_LEVEL", "", "ANIM_TAG_KEY", "", "AUTOMATIC_PAGE_SPACING", "DEBUG", "", "FLING_THRESHOLD_VELOCITY", "INVALID_PAGE", "INVALID_POINTER", "INVALID_RESTORE_PAGE", "MAX_SCROLL_PROGRESS", "MIN_FLING_VELOCITY", "MIN_LENGTH_FOR_FLING", "MIN_SNAP_VELOCITY", "NANOTIME_DIV", "PAGE_SNAP_ANIMATION_DURATION", "REORDERING_DROP_REPOSITION_DURATION", "REORDERING_REORDER_REPOSITION_DURATION", "getREORDERING_REORDER_REPOSITION_DURATION", "()I", "setREORDERING_REORDER_REPOSITION_DURATION", "(I)V", "REORDERING_SIDE_PAGE_HOVER_TIMEOUT", "RETURN_TO_ORIGINAL_PAGE_THRESHOLD", "SIGNIFICANT_MOVE_THRESHOLD", "SLOW_PAGE_SNAP_ANIMATION_DURATION", "TAG", "", "TOUCH_STATE_NEXT_PAGE", "TOUCH_STATE_PREV_PAGE", "TOUCH_STATE_REORDERING", "TOUCH_STATE_REST", "TOUCH_STATE_SCROLLING", "sTmpIntPoint", "", "sTmpInvMatrix", "Landroid/graphics/Matrix;", "sTmpPoint", "", "sTmpRect", "Landroid/graphics/Rect;", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getREORDERING_REORDER_REPOSITION_DURATION() {
            return PagedView.REORDERING_REORDER_REPOSITION_DURATION;
        }

        public final void setREORDERING_REORDER_REPOSITION_DURATION(int i) {
            PagedView.REORDERING_REORDER_REPOSITION_DURATION = i;
        }
    }
}
