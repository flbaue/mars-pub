#! /usr/bin/env ruby

list = ['a','b','c']

list.each do |a|
    list.each do |b|
        puts "#{a}:#{b}"
    end
end
