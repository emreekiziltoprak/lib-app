package com.emrekiziltoprak.libraryapp.loan;

import com.emrekiziltoprak.libraryapp.book.Book;
import com.emrekiziltoprak.libraryapp.member.Member;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LoanPolicy {
    private static final int MAX_ACTIVE_LOANS_PER_MEMBER = 3;

    //kullanınıcn kaç tane kitabı var ? aktif ödünçte
    public Loan borrow(Book book, Member member, LocalDate today, Collection<Loan> existingLoans){

        if(book ==null || member == null || today == null || existingLoans == null ){
            throw new IllegalArgumentException("book or member or today or existingLoans cannot be null");
        }
        //total loans of book unrelated with user
        List<Loan> LoansOfExistingBook = existingLoans.stream().filter(loan -> loan.getBook().equals(book) && loan.isActive()).collect(Collectors.toList());

        List<Book> MemberactiveBooks = existingLoans.stream()
                .filter(loan ->
                loan.getMember().getId().equals(member.getId()) && loan.isActive()
                )
                .map(Loan::getBook)
                .toList();


        if(MemberactiveBooks.size() >= MAX_ACTIVE_LOANS_PER_MEMBER) { throw new IllegalArgumentException("user only can borrow 3 book at once ");}

        List<Book> doesUserHaveBook = MemberactiveBooks.stream().filter(b -> b.equals(book)).toList();
        List<Loan> overdueBooks = existingLoans.stream().filter(loan -> loan.getMember().equals(member) && loan.isOverdue(today)).toList();

        if(doesUserHaveBook.size()>0) {
            throw new IllegalStateException("user already has the book");
        }
        if(overdueBooks.size()>0){
            throw new IllegalStateException("user has overdue books");
        }
        //all are in use
        else if(book.getTotalCopies() == LoansOfExistingBook.size()) {
            throw new IllegalStateException("all copies of book are borrowed");
        }


        return new Loan(book, member, today);
    }
}
