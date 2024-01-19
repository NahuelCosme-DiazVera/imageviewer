package software.ulpgc.imageviewer.file;

import software.ulpgc.imageviewer.Image;
import software.ulpgc.imageviewer.ImageLoader;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Set;

public class FileImageLoader implements ImageLoader {
    private final File[] files;
    private static final Set<String> imageExtensions = Set.of(".png", ".jpg");

    private FileImageLoader(File folder) {
        this.files = folder.listFiles(isImage());
    }

    public static FileImageLoader with(File folder) {
        return new FileImageLoader(folder);
    }

    private FilenameFilter isImage() {
        return (dir, name) -> imageExtensions.stream()
                .anyMatch(name::endsWith);
    }

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String name() {
                return files[i].getAbsolutePath();
            }

            @Override
            public Image next() {
                return imageAt((i + 1) % files.length);
            }

            @Override
            public Image prev() {
                return imageAt((i - 1 + files.length) % files.length);
            }
        };
    }
}
