package me.tobiadeyinka.asciiimg

import java.awt.Color
import java.awt.Image
import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class ImageConverter {

    val WEIGHT_MAX: Int = 255
    val ASCII_CHARS: String = "`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$"
    val ASCII_CHARS_LAST_INDEX: Int = ASCII_CHARS.length - 1

    fun convertImage(image: BufferedImage) {
        val width: Int = image.getWidth(null)
        val height: Int = image.getHeight(null)

        for (i in 0 until height) {
            for (j in 0 until width) {
                val char: Char = getAsciiChar((rgbAverage(Color(image.getRGB(j, i)))))
                print(char.toString().repeat(3))
            }
            println()
        }
    }

    fun resizeBufferedImage(image: BufferedImage, width: Int, height: Int): BufferedImage {
        val tmp = image.getScaledInstance(width, height, Image.SCALE_DEFAULT)
        val resizedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

        val graphics2D = resizedImage.createGraphics()
        graphics2D.drawImage(tmp, 0, 0, null)
        graphics2D.dispose()

        return resizedImage
    }

    private fun rgbAverage(color: Color): Int = (color.red + color.green + color.blue) / 3
    private fun getAsciiChar(weight: Int): Char = ASCII_CHARS[(weight * ASCII_CHARS_LAST_INDEX) / WEIGHT_MAX]

}

fun main(args: Array<String>) {
    ImageConverter().convertImage(ImageIO.read({}.javaClass.getResourceAsStream(args[0])))
}
