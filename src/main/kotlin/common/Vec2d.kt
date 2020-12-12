package common

class Vec2di(var x: Int, var y: Int) {
    operator fun plus(v: Vec2di) = Vec2di(x + v.x, y + v.y)

    operator fun minus(v: Vec2di) = Vec2di(x - v.x, y - v.y)

    operator fun times(s: Int) = Vec2di(s * x, s * y)

    override fun toString() = "($x, $y)"
}