Given /^I am not logged in$/ do
  @current_user = nil
end

Given /^there is (\d+) causes$/ do |nb|
  nb.to_i.times{Cause.make}
end

When /^I delete the (\d+)(?:st|nd|rd|th) landing$/ do |pos|
  visit landings_url
  within("table tr:nth-child(#{pos.to_i+1})") do
    click_link "Destroy"
  end
end

Then /^I should see the following landings:$/ do |expected_landings_table|
  expected_landings_table.diff!(tableish('table tr', 'td,th'))
end
