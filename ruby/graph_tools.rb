require 'set'

# refelxiver abschluß
def r(set)
    set_new = Set.new []
    set.each do |tuple|
        a = tuple[0]
        b = tuple[1]
        set_new << [a,a] << [b,b]
    end
    set + set_new
end

input = Set.new [['a','b'],['b','c']]
erg = r(input)
expected = Set.new [['a','b'],['b','c'],['a','a'],['b','b'],['c','c']]
puts (erg == expected) ? "test: r() =>OK" : "test: r() =>FAILD"

# symetrischer abschluß
def s(list)
    set_new = Set.new []
    set.each do |tuple|
        a = tuple[0]
        b = tuple[1]
        set_new << [b,a]
    end
    set + set_new
end

# transitiver abschluß
def t(set)
    #TODO
end

input = Set.new [['a','b'],['b','c'],['c','d']]
erg = t(input)
expected = Set.new [['a','b'],['b','c'],['c','d'],['a','c'],['a','d']]
puts (erg == expected) ? "test: t() =>OK" : "test: t() =>FAILD"
puts "#{erg.to_a}"
