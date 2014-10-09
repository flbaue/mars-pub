require 'set'

module graph_tools
    
    # Reflexive closure
    def r(set)
        set_new = Set.new []
        set.each do |tuple|
            a = tuple[0]
            b = tuple[1]
            set_new << [a,a] << [b,b]
        end
        set + set_new
    end

    # Symmetric closure
    def s(set)
        set_new = Set.new []
        set.each do |tuple|
            a = tuple[0]
            b = tuple[1]
            set_new << [b,a]
        end
        set + set_new
    end


    # Transitive closure
    def t(set)
        done = false

        until done
            set_new = Set.new []
            set.each do |tuple_a|
                a_a = tuple_a[0]
                a_b = tuple_a[1]

                set.each do |tuple_b|
                    b_a = tuple_b[0]
                    b_b = tuple_b[1]

                    if a_b == b_a
                        tuple_new = [a_a,b_b]
                        set_new << tuple_new
                    end
                end
            end

            done = set_new.proper_subset? set
            set = set + set_new
        end
        set
    end
end

#r() Test
input = Set.new [['a','b'],['b','c']]
erg = r(input)
expected = Set.new [['a','b'],['b','c'],['a','a'],['b','b'],['c','c']]
puts (erg == expected) ? "test: r() =>OK" : "test: r() =>FAILD"

#s() Test
input = Set.new [['a','b'],['b','c']]
erg = s(input)
expected = Set.new [['a','b'],['b','c'],['b','a'],['c','b']]
puts (erg == expected) ? "test: s() =>OK" : "test: s() =>FAILD"

#t() Test
input = Set.new [['a','b'],['b','c'],['c','d']]
erg = t(input)
expected = Set.new [['a','b'],['b','c'],['c','d'],['a','c'],['a','d'],['b','d']]
puts (erg == expected) ? "test: t() =>OK" : "test: t() =>FAILD"
